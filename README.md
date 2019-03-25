# git-cleanup-kscript

Kotlin Script for clean-up of feature branches in github-flow style projects.

When working in feature branches those tend to pile up over time in you local repo and deleting all the branches manually can become tedious. Therefore this script becomes useful. It compares the list of local and remote branches and deletes any local branch that is not present on the remote (assuming that remote branch has been successfully merged).

## Requirements

Script is written assuming you have [kscript](https://github.com/holgerbrandl/kscript#installation) installed on your machine.

You can verify that everything is ready by running `kscript --help` in any directory.

`cd` into this repo directory or use full/relative path to the script file from an arbitrary folder

## Usage

``` bash
kscript git-cleanup.kts <path to the project repository> <your main development branch>
```

Examples:

``` bash
kscript git-cleanup.kts ../sandboxproject master

kscript ..path/to/script/git-cleanup.kts /sandboxproject
```

If not provided default branch is `develop`.

## Things to improve

* Instruction to add this to global enviroment

* Proper help command, e.g. `--help`

* Correct message when attempting to delete branch that has not been merged yet.

* Extra checks to avoid accidentally delete anything.