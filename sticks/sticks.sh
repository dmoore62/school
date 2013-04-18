#!/bin/bash
varnum=$RANDOM
varnum=$[ $varnum % 1000 ]
echo $varnum
varcount=0
while [ $varcount -lt $varnum ]; do
	stlen=$RANDOM
	stlen=$[$stlen % 10000]
	echo $stlen
	numcuts=$RANDOM
	numcuts=$[$numcuts % 100]
	echo $numcuts
	innercount=0
	while [ $innercount -lt $numcuts ]; do
		lowlimit=1
		cut[$innercount]=$[$lowlimit + ($RANDOM % ($stlen - 1))]
		#echo ${cut[$innercount]}
		let lowlimit=cut+1
		let innercount=innercount+1
	done
	size=0
	for ((last=numcuts-1; last > 0; last --))
	do
		for ((i=0; i<last; i++))
		do
			j=$((i+1))
			if [ ${cut[i]} -gt ${cut[j]} ]
				then

				temp=${cut[i]}
				cut[$i]=${cut[j]}
				cut[$j]=$temp

			fi
		done
	done
	echo ${cut[@]}
	unset cut
	let varcount=varcount+1
done