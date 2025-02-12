# Bungee-Command-Blocker
A Simple Plugin That Allows You To Block Certain Commands In Certain Servers Via Bungee.

## 📥 Installation
1. Download the `.jar` file  
2. Copy and paste it into your **BungeeCord** server's `plugins` folder  
3. Start (or restart) your BungeeCord server  

## ⚙️ Configuration
You can modify the blocked servers, blocked commands, and error messages in `config.yml`.  

```yaml
# Blocked Server(s)
blocked-servers:
  - "Auth"

# Blocked Command(s)
blocked-commands:
  - "server"
  - "send"
  - "connect"

# Error Message
error-message: "&cYou cannot use this command on this player!"

# /send all (Server) Error Message
all-players-message: "&aSent {AMOUNT_OF_PLAYERS} players to {SERVER}, &eExcept {AMOUNT_OF_PLAYERS_IN_BLOCKED_SERVER} players which were in a blocked server."
