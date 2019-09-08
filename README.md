# The project includes applications for demoing rsocket protocol

The applications are:
 web gateway - exposes  the rest API and handles authentication and input validation.
 notes ms - exposes rsocket protocol based reactive API for managing the notes.
 user ms - exposes rsocket protocol based reactive API for identifying the authenticated user.
 
Run:
"docker-compose up" (if there are any issues with DB setup pls run "docker-compose down -v" before)

Try to open
http://localhost:8080/api/note/ you will be redirected to google site and after successful login, you will be redirected back.

Currently, only google's oauth2 is confugred so to access resources you have to
use cookies based authentication - google uses something like
 "__tawkuuid=e::localhost::k7Yps/PlAycRQZpNDiapMW5eYwaE+qTH40uhpogZj9suKZEZo1ZljVaTospsXK42::2; SESSION=4a1cafab-9c47-4915-9870-ae0dbe68d6cd"
and the last part SESSION=4a1cafab-9c47-4915-9870-ae0dbe68d6cd includes token for auth, so you can just copy this part from your broser and use for curl commands.


Get all notes for the user
----------------------

curl 'http://localhost:8080/api/note/' -H 'Connection: keep-alive' -H 'Cache-Control: max-age=0' -H 'Upgrade-Insecure-Requests: 1' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36' -H 'Sec-Fetch-Mode: navigate' -H 'Sec-Fetch-User: ?1' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3' -H 'Sec-Fetch-Site: none' -H 'Accept-Encoding: gzip, deflate, br' -H 'Accept-Language: en-US,en;q=0.9,nl;q=0.8' -H 'Cookie: __tawkuuid=e::localhost::k7Yps/PlAycRQZpNDiapMW5eYwaE+qTH40uhpogZj9suKZEZo1ZljVaTospsXK42::2; SESSION=f82513d3-811b-40d4-9666-3d535c3bf733' --compressed

Create notes for the user
----------------------
curl -X POST \
  http://localhost:8080/api/note/ \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Cookie: __tawkuuid=e::localhost::k7Yps/PlAycRQZpNDiapMW5eYwaE+qTH40uhpogZj9suKZEZo1ZljVaTospsXK42::2; SESSION=4a1cafab-9c47-4915-9870-ae0dbe68d6cd' \
  -d '{"title":"aa","text":"dsdsd"}'

Update the note
----------------------
curl -X PUT \
  http://localhost:8080/api/note/ \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Cookie: __tawkuuid=e::localhost::k7Yps/PlAycRQZpNDiapMW5eYwaE+qTH40uhpogZj9suKZEZo1ZljVaTospsXK42::2; SESSION=4a1cafab-9c47-4915-9870-ae0dbe68d6cd' \
  -d '{"title":"aa","text":"dsdsd","noteId":1}'


