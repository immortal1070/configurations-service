DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

mvn install -pl configurations-backend && sh $DIR/scripts/packageDev.sh
