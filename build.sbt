Settings.settings

val rootFile = file(".")
val aRoot    = rootFile / "a"

val a01 = project in aRoot / "a01"
val a02 = project in aRoot / "a02"
val a03 = project in aRoot / "a03"
val a04 = project in aRoot / "a04"
val a05 = project in aRoot / "a05"
val a06 = project in aRoot / "a06"

val bRoot = rootFile / "b"
val b01   = project in bRoot / "b01"

val cRoot = rootFile / "c"
val c01   = project in cRoot / "c01"