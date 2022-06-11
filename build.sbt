Settings.settings

val rootFile = file(".")
val aRoot    = rootFile / "a"
val dRoot    = rootFile / "d"
val eRoot    = rootFile / "e"

val a01   = project in aRoot / "a01"
val a02   = project in aRoot / "a02"
val a03   = project in aRoot / "a03"
val a04   = project in aRoot / "a04"
val a05   = project in aRoot / "a05"
val a06   = project in aRoot / "a06"
val a07   = project in aRoot / "a07"
val a08   = project in aRoot / "a08"
val wip04 = project in aRoot / "wip04"

val d02 = project in dRoot / "a02"
val d03 = project in dRoot / "a03"
val d04 = project in dRoot / "a04"
val d05 = project in dRoot / "a05"
val d06 = project in dRoot / "a06"

val bRoot = rootFile / "b"
val b01   = project in bRoot / "b01"

val cRoot = rootFile / "c"
val c01   = project in cRoot / "c01"

val e01   = project in eRoot / "e01"
