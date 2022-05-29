Settings.settings

val rootFile = file(".")
val aRoot    = rootFile / "a"
val dRoot    = rootFile / "d"

val a01 = project in aRoot / "a01"
val a02 = project in aRoot / "a02"
val a03 = project in aRoot / "a03"

val d02 = project in dRoot / "a02"
val d03 = project in dRoot / "a03"
val d04 = project in dRoot / "a04"
val d05 = project in dRoot / "a05"
val d06 = project in dRoot / "a06"

val bRoot = rootFile / "b"
val b01   = project in bRoot / "b01"

val cRoot = rootFile / "c"
val c01   = project in cRoot / "c01"