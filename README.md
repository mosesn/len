#len
A *nix tool for ensuring all of your lines are shorter than a specified length.  
Specifically for use with git hooks, so that you can ensure your lines stick to  
the project's standard.

#returns
1 if your lines are too long  
0 if your lines are not too long

#arguments
-h for help  
-n int for maximum line length  
last argument is the file name.  if it's omitted, stdin is accepted.