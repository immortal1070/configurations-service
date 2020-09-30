DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

mvn install -pl configurations-dbupdate && sh $DIR/scripts/package-dev-minimal.sh
