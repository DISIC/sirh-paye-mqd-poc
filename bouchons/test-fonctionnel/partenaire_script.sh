typeset -i ok=0
typeset -i res=0
newline = ""
function change
{
	set -o xtrace
	nline = $(echo $1 | sed 's!VALUES[ ]*(.*,!VALUES (nextval('SEQ_participant')!')
	echo "NEWLINE :" $nline
	return $nline
}
while read line
do
	echo -e "$line"
	if (($ok)) 
	then
		echo OK
		ok=0
	fi
	echo $line | grep -i "INSERT INTO participant" 2>&1 1>/dev/null
	res=$?
	echo "grep = $res"
	if (($res))
	then
		ok=0
	else
		echo $line | grep -i "VALUES" 2>&1 1>/dev/null
		if ((!$?))
		then
			newline = 
		fi
	fi
done
