package scripts

import com.squareup.kotlinpoet.*

data class AlgoFunction(
    val fileName: String,
    val name: String,
    val args: List<Pair<String, TypeName>>,
    val returnType: TypeName? = null
): Dsa()

data class DS(
    val name: String,
    val functions: List<AlgoFunction>,
    val props: Props? = null,
    val receiverType: ReceiverType? = null
) : Dsa()

data class Props(
    val primaryConstructor: Boolean = false,
    val fields: List<Pair<String, TypeName>>? = null
)

data class ReceiverType(
    val hasMultiple: Boolean = false
)

sealed class Dsa

fun main(args: Array<String>) {
    val packageName = "day${args.first().toInt()}"
    val algos = args.takeLast(args.size - 1).map { Algos.valueOf(it) }
    val algoFunctions = algos.map {
        when (it.algoType) {
            is AlgoFunction -> {
                buildAlgoFunction(packageName, it.algoType)
            }
            is DS -> {
                buildDS(packageName, it.algoType)
            }
        }
    }
    algoFunctions.forEach {
        println("----${it.name}----")
        it.writeTo(System.out)
    }
}

fun buildAlgoFunction(packageName: String, func: AlgoFunction): FileSpec {
    return with(func) {
        FileSpec.builder(packageName, fileName)
            .addFunction(
                FunSpec.builder(name).apply {
                    for (arg in args) {
                        addParameter(
                            name = arg.first,
                            type = arg.second
                        )
                    }
                    returnType?.let {
                        returns(it)
                    }
                }.build()
            )
            .build()
    }
}

fun buildDS(packageName: String, ds: DS): FileSpec {
    return with(ds) {
        FileSpec.builder(packageName, name).apply {
            addType(
                TypeSpec.classBuilder(name).apply {
                    receiverType?.let {
                        addTypeVariable(t)
                        if (it.hasMultiple) {
                            addTypeVariable(v)
                        }
                    }
                    props?.let {
                        if (it.primaryConstructor) {
                            primaryConstructor(
                                FunSpec.constructorBuilder().apply {
                                    it.fields?.let { flds ->
                                        for (field in flds) {
                                            addParameter(name = field.first, type = field.second)
                                            addProperty(
                                                PropertySpec.builder(field.first, field.second)
                                                    .initializer(field.first)
                                                    .addModifiers(KModifier.PRIVATE)
                                                    .mutable(true)
                                                    .build()
                                            )
                                        }
                                    }
                                }
                                    .build()
                            )
                        } else {
                            it.fields?.let {flds ->
                                for (field in flds) {
                                    addProperty(
                                        PropertySpec
                                            .builder(name = field.first, type = field.second)
                                            .mutable(true)
                                            .build()
                                    )
                                }
                            }
                        }
                    }
                    for (func in functions) {
                        addFunction(
                            with(func) {
                                FunSpec.builder(func.name).apply {
                                    for (arg in args) {
                                        addParameter(
                                            name = arg.first,
                                            type = arg.second
                                        )
                                    }
                                    returnType?.let {
                                        returns(it)
                                    }
                                }.build()
                            }
                        )
                    }
                }.build()
            )
        }.build()
    }
}