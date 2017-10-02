#!/ bin / bash
set -x

for f in ./*.java
do
  echo "Processing $f ..."
  # take action on each file. $f store current file name
  clang-format $f > "$f 2"
  cat "$f 2" > $f
  rm "$f 2"
done
