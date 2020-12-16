# CyberScoreServer
A java web app based scoreboard parsing and filtering application. 
## Please note this only works when the scoreboard at [scoreboard.uscyberpatriot.org](http://scoreboard.uscyberpatriot.org) is live!
## Setup
Use the .war file from releases to deploy this web app to your Apache Tomcat server. After installation on server, go to `/board-admin` to setup scoreboard URL and the team prefix (ex. 12-)

## Commands
#### !team <last four of team ID> - show team detail
  ex. `!team 1301`
#### !scoreboard <parm1> <parm2> <parm3> - parm options can be in any order and either a state abbreviation, tier, or division - shows a filtered view of the public scoreboard
  ex. `!scoreboard NC AS Gold`
  ex. `!scoreboard Platinum NC Mid`
  ex. `!scoreboard Open Silver NC`
  ##### Order of parameters does NOT matter
#### !help - for help page
