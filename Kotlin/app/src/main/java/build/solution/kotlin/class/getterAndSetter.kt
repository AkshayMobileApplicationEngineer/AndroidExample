package build.solution.kotlin.class

import Box

class getterAndSetter {
    val box1=Box()
    val volume
        get()=box1.heigth

    print(volume)

    var boxName="Pooja"
    print(boxName)
}