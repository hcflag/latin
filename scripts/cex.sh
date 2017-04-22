#!/bin/bash
#
# Assembles catalog and contents of all cataloged editions in a single file
# in  CITE Exchange (cex) format, replacing all tab characters with the pound
# sign "#". Writes to standard output: redirect as you like.
#
# Run this script from the root directory of the flag latin repository.
#
# Example:
#
#    ./scripts/cex.sh > flag.cex
#

cat cextemplates/catalogdiv.txt editions/flag_catalog.tsv cextemplates/datadiv.txt  editions/cataloged/*tsv | sed -e $'s/\t/#/g'
