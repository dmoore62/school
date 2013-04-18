#!/bin/bash
casenum=$RANDOM
casenum=$[1 + ($casenum % 1000)]
echo $casenum
index=0
for x in {A..Z}
	do
		alpha[$index]=$x
		let index=index+1
	done
maini=0
while [ $maini -lt $casenum ];
do
	word1=$RANDOM
	word2=$RANDOM
	word1=$[1 + ($word1 % 1000)]
	word2=$[1 + ($word2 % 5)]
	for ((i=0; i<word1; i++))
	do
		letter1=$RANDOM
		letter1=$[$letter1 % 25]
		thisword[$i]=${alpha[letter1]}
	done
	#echo ${thisword[@]} | cut -d "," -f 1-${#thisword[@]}
	printf %s ${thisword[@]}
	echo -e "\n"
	for ((j=0; j<word2; j++))
	do
		letter2=$RANDOM
		letter2=$[$letter2 % 25]
		thatword[$j]=${alpha[letter2]}
	done
	printf %s ${thatword[@]}
	echo -e "\n"
	let maini=maini+1
done