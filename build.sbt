Settings.settings

val rootFile = file(".")
val aRoot    = rootFile / "a"
val bRoot    = rootFile / "b"

val a02 = project in aRoot / "a02"
val a03 = project in aRoot / "a03"
val a04 = project in aRoot / "a04"
val a05 = project in aRoot / "a05"
val a06 = project in aRoot / "a06"

val b05 = project in bRoot / "b05"
val b06 = project in bRoot / "b06"
val b07 = project in bRoot / "b07"
val b08 = project in bRoot / "b08"
val b09 = project in bRoot / "b09"
val b10 = project in bRoot / "b10"
