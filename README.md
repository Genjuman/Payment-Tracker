Here is a list of my assumptions:

	1) I've decided to use daemon thread for displaying output part of currencies. Timing is realized by letting this thread sleep for 1 minute between every displaying.
	
	2) I've decided to use singleton pattern for my Records class.
	
	3) For storing data in Records class, I've decided to use ConcurrentHashMap. Because it's supposed to be thread safe and both threads can access it at the same time.
	
	4) When user input anything (both good or bad input), there is allways output which tells him or her, what just happened.
	
	5) For realization of bonus question, I've added exchange ratting to my Currencies enumeration.
	
	6) For keep this exercise simple, I;ve decided to use only four Currencies (EUR, USD, RUR, GBP). You can write them in lower or upper case - both works.
	
	7) For keeping amount records, I've used BigDecimal. Because of its precious which we need especially when we work with money.
	
	8) Input is loaded from a file when program starts and currencies state is also saved into file, when program ends propperly (input "quit" - both upper or lower case).
	
	9) Both for displaying and saving to file, currencies with zero amount are excluded

How to run my program:

	via some IDE:
		1) simply by runnig /src/core/Run.java

	Via command line (after building):
		1) Run your command line
		2) Navigate to directory, where jar file appears. (For example Netbeans IDE "PaymentTracker\dist\" directory.)
		3) input: "java -jar PaymentTracker.jar"
