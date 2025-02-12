# Bungee-Command-Blocker
A Simple Plugin That Allows You To Block Certain Commands In Certain Servers Via Bungee./n
How to install :
1. Download the .jar file
2. Copy and paste it into your Bungee server's plugins folder
3. Run your bungee server and you're all set to go!
How it works :
You can change the blocked server, blocked commands and the error messages in the config.yml
```
# Blocked Server(s) :
blocked-servers:
  - "Auth"
# Blocked Command(s) :
blocked-commands:
  - "server"
  - "send"
  - "connect"
# Error Message :
error-message: "&cYou cannot use this command on this player!"
# /send all (Server) Error Message :
all-players-message: "&aSent {AMOUNT_OF_PLAYERS} players to {SERVER}, &eExcept {AMOUNT_OF_PLAYERS_IN_BLOCKED_SERVER} players which were in a blocked server."
```
