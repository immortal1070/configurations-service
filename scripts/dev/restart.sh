DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
sh $DIR/scripts/jar/shutdown.sh && sh $DIR/scripts/dev/start-dev.sh
