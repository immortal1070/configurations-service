DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
sh $DIR/scripts/shutdown.sh && sh $DIR/scripts/startDev.sh && sh $DIR/scripts/packageDev.sh
