
<h5>Setup Instructions:</h5>

1. Clone the repo using following command in terminal:<br>
`git pull https://github.com/akarki15/suffix.git`
2. Open Eclipse. Go to `File` -> `Import` to open the Import dialog box. 
3. Select `Existing Projects into Workspace`. Click `Next`.
4. Choose `Select root directory`. Browse to the location where you cloned the repo in step 1. 
5. Uncheck `Copy projects into workspace` to avoid making a duplicate copy of the repo. 
6. Select `Finish`

<h5>Checking the status of local repo:</h5>

You can see the changes you made by browsing to the local repo's location in terminal and using the `git status`command. This shows the differences of local repo compared to the head of main branch (of remote server).

<h5>Commiting and pushing changes:</h5>
After you make changes to the local repo, you can commit and push changes to the main branch.  

1. Use `git add .` to track all the new files you might have added to the local repo. This adds all the files you created to remote server. Or you can manually add individual files. Check out `git --help add` for more info. 
2. Use `git commit -m "<your message goes here>"` to commit the changes you have made. Note, This <b>does not</b> update the remote github repo. 
3. Use `git push` to push the changes to remote server. More info [here](https://help.github.com/articles/pushing-to-a-remote/).
