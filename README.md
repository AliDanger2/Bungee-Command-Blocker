# Bungee-Command-Blocker
A Simple Plugin That Allows You To Block Certain Commands In Certain Servers Via Bungee.

## 📥 Installation
1. Download the `.jar` file  
2. Copy and paste it into your **BungeeCord** server's `plugins` folder  
3. Start (or restart) your BungeeCord server  

## ⚙️ Configuration
You can modify the blocked servers, blocked commands, and error messages in `config.yml`.  

```yaml
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
# /send Error Messages :
no-players-moved-message: "&cNo players were moved because all the players were in a blocked server."
all-players-moved-message: "&aSent {AMOUNT_OF_PLAYERS} {PLAYER_TEXT} to {SERVER}."
partial-players-moved-message: "&aSent {AMOUNT_OF_PLAYERS} {PLAYER_TEXT} to {SERVER}, &eexcept {AMOUNT_OF_PLAYERS_IN_BLOCKED_SERVER} {EXCEPTION_PLAYER_TEXT} which were in a blocked server."
```
## 📌 Features
✅ Block specific commands on selected servers
<br>
✅ Fully customizable messages and blocked commands via config.yml
<br>
✅ Lightweight and efficient for BungeeCord networks
<br>
✅ Can reload the config using ``/bcb reload``

## 📜License
This plugin is open-source and free to use.

## ⭐ Support & Feedback
If you encounter issues or have suggestions, feel free to leave a comment!
