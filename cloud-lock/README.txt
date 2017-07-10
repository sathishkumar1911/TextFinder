1) How to build this code?
This is maven project so basic maven commands are enough to build this.

mvn clean install

2) How to run this app?
After successful maven commands the jar file will be placed in target folder.
This jar file will take the current folder as base folder. 
If you want to run in different machine, just copy and paste the jar file to the destination machine.

To run and test,
Keep your folder in the same place where the jar is executed.

java -cp cloud-lock-0.0.1-SNAPSHOT.jar com.cl.sample.TextFinder <foldername> <texttosearch>

"foldername" and "texttosearch" inputs are mandatory to search. 
You have to enter the foldername and texttosearch if you miss to give in the command.

3) Test Code and Test data
As third party libraries are restricted I removed junit and wrote simple java test cases. 
It will be executed while building project through maven.
Test code available in Cloud_Lock\cloud-lock\src\test\java\com\cl\sample\TextFinderTest.java file.

4) Sample request and output
There is a sample folder "samplefolder" in the project. 

Command to execute the jar for the sample folder

java -cp target/cloud-lock-0.0.1-SNAPSHOT.jar com.cl.sample.TextFinder samplefolder sample

Sample output:
---------------
<abspath>\Cloud_Lock\cloud-lock\samplefolder\empty.out:MISS
<abspath>\Cloud_Lock\cloud-lock\samplefolder\sample\empty.out:MISS
<abspath>\Cloud_Lock\cloud-lock\samplefolder\sample\Hit.cap:MISS
<abspath>\Cloud_Lock\cloud-lock\samplefolder\sample\hit.txt:MISS
<abspath>\Cloud_Lock\cloud-lock\samplefolder\sample\miss.me:MISS
<abspath>\Cloud_Lock\cloud-lock\samplefolder\sample.txt:HIT
<abspath>\Cloud_Lock\cloud-lock\samplefolder\subfolder\sample.txt:MISS

Showing absolute path to know the exact subfolder location