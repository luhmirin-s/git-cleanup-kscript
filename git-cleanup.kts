#!/usr/bin/env kscript

import java.io.File
import kotlin.system.exitProcess

fun dropDownDead(msg: String): Nothing {
    println(msg)
    exitProcess(-1)
}

fun File.execute(command: String) = Runtime.getRuntime()
        .exec(command, null, this)
        .inputStream
        .bufferedReader()
        .readLines()

val dir = args.getOrNull(0)
        ?.let { File(it) }
        ?.takeIf { it.exists() }
        ?: dropDownDead("Argument is not a valid directory")

val localBranches = dir.execute("git branch --list").map { it.replace("*","").trim() }
val mainBranch = args.getOrNull(1)?.takeIf { localBranches.contains(it) }?: "develop"

println("Switching to $mainBranch")
dir.execute("git checkout $mainBranch")

val remoteBranches = dir.execute("git branch --list -r").map { it.substringAfterLast("/").trim() }
val diff = localBranches.filter { !remoteBranches.contains(it) }

println("Found ${diff.size} branches without corresponding remote.")
diff.forEach {
    println("Deleting $it")
    dir.execute("git branch -d $it")
}