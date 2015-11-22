#!/bin/bash
FILE=$1
SPRAY_PROJECT="/home/amit/Desktop/grymco/spray/grymco-spray-exam/target/scala-2.11/grymco-spray-exam-assembly-1.0"
AKKA_PROJECT="/home/amit/Desktop/grymco/Akka/target/scala-2.11/Akka-assembly-1.0.jar"
fiveLinesFileName="output5words.txt"
fileTORead="/home/amit/Desktop/grymco/file2read.txt"

if [ -f $FILE ];
then
   echo "File $FILE exists"
   nohup scala $SPRAY_PROJECT &
   sleep 10
   scala $AKKA_PROJECT $fileTORead
   sort -r -k2,2 -k1,1 <$fileTORead= > sorted.txt
   ( head -1 fileWithNumberOfWords.txt  ) > $fiveLinesFileName=
   while read line; do
   curl --request GET 'http://localhost:8080?reversestring&string=amit'
   done < $fiveLinesFileName
   rm -rf  $fiveLinesFileName
   rm -rf sorted.txt
   echo "Done"
else
   echo "File $FILE does not exists"
   exit 1
fi
