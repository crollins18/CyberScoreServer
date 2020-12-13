# CyberScoreServer
## Please note this only works when the scoreboard at [scoreboard.uscyberpatriot.org](http://scoreboard.uscyberpatriot.org) is live!
## Setup
A java servlet based scoreboard parsing and filtering application. For deployment, I recommend using Eclipse for Java EE Developers and the Azure Web Application add-on to Eclipse for seamless transfer to an Azure web app. Of course, uploading this straight to your Tomcat server also works.

After installation on server, go to `/board-admin` to setup scoreboard URL and the team prefix (ex. 12-). IF this is not done, errors when loading pages will occur.

## Commands
#### !team <last four of team ID> - show team detail
  ex. `!team 1301`
#### !monitor <last four of team ID> <last four of another team ID> - monitor two teams with notifications
  ex. `!monitor 3010 0045`
#### !scoreboard <parm1> <parm2> <parm3> - parm options can be in any order and either a state abbreviation, tier, or division - shows a filtered view of the public scoreboard
  ex. `!scoreboard NC AS Gold`
  ex. `!scoreboard Platinum NC MS`
  ex. `!scoreboard Open Silver NC`
  ##### Order of parameters does NOT matter
#### !help - for help page

## Examples
### Home Page
![Screenshot](homepage.png)

### Filtered View (ex. MS teams in NC)
![Screenshot](MSfilterEx.PNG)
