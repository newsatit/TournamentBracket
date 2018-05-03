all: src/application/*.java
	javac src/application/*.java
    
# if your java files are in src (place your teams??.txt files there too)
teams00: 
	java -cp src application.Main src/teams00.txt > teams00.log
        
teams01: 
	java -cp src application.Main src/teams01.txt > teams01.log
    
teams02: 
	java -cp src application.Main src/teams02.txt > teams02.log
    
teams04: 
	java -cp src application.Main src/teams04.txt > teams04.log
    
teams08: 
	java -cp src application.Main src/teams08.txt > teams08.log
    
teams16: 
	java -cp src application.Main src/teams16.txt > teams16.log

teams32: 
	java -cp src application.Main src/teams32.txt > teams32.log
    
# CREATE executable.jar files
# make jar     - IF .java FILES ARE NOT IN src folder
jar:
	jar -cmf src/manifest.txt src/executable.jar src/application
    
# make jar_src - IF .java FILES ARE IN src folder
jar_src:   
	jar -cmf src/manifest.txt executable.jar src/application

clean:
	\rm src/application/*.class
