
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class Main {
	public static int numDocuments;
	public static  ArrayList<Items> list = new ArrayList<Items>();
	public static Map<String, Dict> hash  = new HashMap<String, Dict>();
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
	
		//cleanfiles();
		//removeDuplicates();
		createSortedCollection();// call  above code only once to form sorted results
		createCollectionFromFile();
		createDictionary();
		System.out.println(numDocuments);
		System.out.println(list.size());
		System.out.println(hash.size());
		getWordTfidf("area");
		getWordTfidf("article");
		getWordTfidf("bad");
		//System.out.println(getWordTfidf("against"));

		System.out.println(getCategory("article"));
		//getPostings("article");
		//getPostings("performance");
		//getPostings("read");
		//getPostings("window");
		//getPostings("subject");

	}

	public static double getIDF(int numDocsContaingWord ){
		//System.out.println(numDocuments);
		//System.out.println(numDocsContaingWord);
		return  Math.log10(((numDocuments*1.0)/numDocsContaingWord));
	}

	public static Double getWordTfidf(String term){
		double tfidf=0.0;
		if(!hash.containsKey(term)){
			System.out.println("Term not in the dictionary");
		} else{
			Dict dic = hash.get(term);
			tfidf = getIDF(dic.DocFrecuency)*dic.documentTermFrequency;
			System.out.println(dic.term + " " +dic.DocFrecuency +" "+dic.documentTermFrequency + " "+ tfidf +" "+dic.docIds);

	}return tfidf;
	}
	public static int  getCategory(String term) {
		int category1 =1;
		int category2 =2;
		int category3 =3;
		int category4 =4;
		if (!hash.containsKey(term)) {
			System.out.println("Term not in dictionary");
		} else {
			Dict dic = hash.get(term);
			switch (category1) {
				case 1:
					for(int a:dic.docIds){
					if (dic.docIds.contains(1)||dic.docIds.contains(2)||dic.docIds.contains(3)|| dic.docIds.contains(4)||dic.docIds.contains(5))
					return 1;}
				case 2:
					for(int a:dic.docIds){
						if (dic.docIds.contains(6)||dic.docIds.contains(7)||dic.docIds.contains(8)|| dic.docIds.contains(9)||dic.docIds.contains(10))
							return 2;}
				case 3:
					for(int a:dic.docIds){
						if (dic.docIds.contains(11)||dic.docIds.contains(12)||dic.docIds.contains(13)|| dic.docIds.contains(14)||dic.docIds.contains(15))
							return 3;}
				case 4:
					for(int a:dic.docIds){
						if (dic.docIds.contains(16)||dic.docIds.contains(17)||dic.docIds.contains(18)|| dic.docIds.contains(19)||dic.docIds.contains(20))
							return 4;}
				case 5:
					for(int a:dic.docIds){
						if (dic.docIds.contains(31)||dic.docIds.contains(22)||dic.docIds.contains(23)|| dic.docIds.contains(24)||dic.docIds.contains(25))
							return 5;}
			}
		}
		return 0;
	}

	public static void getDiceProbability(){

	}
	public static void getPostings(String term) throws FileNotFoundException{
		if(!hash.containsKey(term)){
			System.out.println("Term not in the dictionary");
		} else{
			Dict dic = hash.get(term);
			System.out.println(term + " is in " + dic.DocFrecuency + " files");

			
			//File file = new File(dic.PostingListPath);
			File file = new File("C:\\git\\bonus\\output\\output.txt/");

			StringTokenizer st;	
			
	        Scanner sc = new Scanner(file);               
	        int docId = 0;
	        int frequency = 0;
			String tempWord;
			System.out.println();
			System.out.println("DocId:\tFrequency:");
			//while (sc.hasNextLine()) {  // Use if we want to retrieve all elements.
			for (int f = 0 ; f<50 ; f++){
				st = new StringTokenizer(sc.nextLine());
	            while(st.hasMoreTokens()){
	            		//tempWord=st.nextToken();
						docId = Integer.parseInt(st.nextToken());
	            		frequency = Integer.parseInt(st.nextToken());
	            		System.out.println(docId + "\t" + frequency);
	            		}
	            }            
				sc.close(); 		
			}
	}
		
	
	public static void createDictionary(){		
		
		for(Items term : list){
			Dict temp = hash.get(term.term);
			String path;
		
			if(temp == null){
				ArrayList<Integer> tempDocIds = new ArrayList<Integer>();
				tempDocIds.add(term.docId);
				temp = new Dict(term.term, 1, "output/" + term.term + ".txt", term.frequency, tempDocIds );
				path = temp.PostingListPath;
				hash.put(term.term, temp);
				numDocuments++;
				//System.out.println(numDocuments);
				//System.out.println(term.term +" " + temp.term + " "+ temp.DocFrecuency+" "+temp.PostingListPath+" "+ term.frequency);
			} else{
				temp.DocFrecuency++;
				temp.docIds.add(term.docId);
				hash.put(term.term, temp);
				path = temp.PostingListPath;
			}
			//below code is to store the posting list on the disk
		/*
			//System.out.println(path);
			File file = new File(path);
			try {
				file.createNewFile();
				BufferedWriter bw = null;			 
			      try {
		
			         bw = new BufferedWriter(new FileWriter(file, true));
				     bw.write(term.docId + " " + term.frequency);
				     bw.newLine();
				     bw.flush();
			      } catch (IOException ioe) {
			    	  ioe.printStackTrace();
			      } finally {                       // always close the file
			    	  if (bw != null) try {
			    		  bw.close();
			     } catch (IOException ioe2) {
			        // just ignore it
			     }
			  }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}
		
		
	}

	//adds the sorted list into our list
	public static void createCollectionFromFile() throws FileNotFoundException{
		
		File file = new File("output/output.txt");
		
		StringTokenizer st;
		
		
        Scanner sc = new Scanner(file);               
        int docId = 0;
		String temp = "";
		int frequency;			
		
		while (sc.hasNextLine()) {
			st = new StringTokenizer(sc.nextLine());
            while(st.hasMoreTokens()){
            		temp = st.nextToken();                		
            		frequency = Integer.parseInt(st.nextToken());
            		docId = Integer.parseInt(st.nextToken());
            		list.add(new Items(temp, frequency, docId));
				//System.out.println(temp + " "+frequency + " "+docId +getWordTfidf(temp));
			}
            }            
			sc.close(); 				
				
		}   
		
	//method to be run only once to create the posting list files
	public static void createSortedCollection() throws FileNotFoundException, UnsupportedEncodingException {

		File file = new File("inpu/");
		File[] files = file.listFiles();
		
		StringTokenizer st;
		
		for (int i = 0; i < files.length; i++) {
            Scanner sc = new Scanner(files[i]);               
			String[] docIdTemp = files[i].getName().split("\\.");
			int docId = Integer.parseInt(docIdTemp[0]);
			String temp = "";
			int docTermFrequency;
			
			while (sc.hasNextLine()) {
				st = new StringTokenizer(sc.nextLine());
            	while(st.hasMoreTokens()){
            		temp = st.nextToken();
					docTermFrequency = Integer.parseInt(st.nextToken());
            		
            		list.add(new Items(temp, docTermFrequency, docId));
					//System.out.println(temp + " " + docTermFrequency+ " "+docId);
				}
            }            
			sc.close(); 		
		}   
		 Collections.sort(list, new Comparator<Items>(){
             public int compare(Items t1, Items t2){
            	 String term1 = t1.term;
            	int termComp = term1.compareTo(t2.term);
            	int result = 0;
         		
            	if(termComp < 0){
         			return -1;
         		} 
         		if(termComp > 0){
         			return 1;
         		} else{
         			if(termComp == 0){
         				
             			if(t1.docId >= t2.docId){
             				result = 1;         			
             			} else{
             				result = -1;
             			}
             			return result;
             		}
         		}
				return result;
           }});
		
		PrintWriter writer = new PrintWriter("output/output.txt", "UTF-8");
		for(int j = 0; j <list.size(); j++){
			 
             writer.println(list.get(j).toString());              
            
			//System.out.println(list.get(j).toString());
		}
		 writer.close();			          
        
        }
	
	//used to delete the duplicates from the file
	public static void removeDuplicates() throws UnsupportedEncodingException{
		
		HashMap<String, Integer> checkDuplicates = new HashMap<String, Integer>();
		
		File file = new File("inpu/");
		File[] files = file.listFiles();
		
		StringTokenizer st;
		
		for (int i = 0; i < files.length; i++) {
            //System.out.println(files[i]);
            try {

                Scanner sc = new Scanner(files[i]);               
                String temp = "";
                String str = "";
                //System.out.println(files[i]);
                while (sc.hasNextLine()) {
                	st = new StringTokenizer(sc.nextLine());
                	while(st.hasMoreTokens()){
                		temp = st.nextToken();                		
                		if(checkDuplicates.get(temp) == null) {
                		    // This word has not been found anywhere before,
                		    // so create a Map to hold document-map counts.                		    
                		    checkDuplicates.put(temp, Integer.parseInt(st.nextToken()));
                		} else {
                			int actual = checkDuplicates.get(temp);
                			checkDuplicates.put(temp, (Integer.parseInt(st.nextToken())+actual));
                			
                		}
                		
                	}
                }              
                sc.close();     
                Iterator it = checkDuplicates.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    str += (pair.getKey() + " " + pair.getValue()+"\n");
                    it.remove(); // avoids a ConcurrentModificationException
                }
                //System.out.println(str);                             
                PrintWriter writer = new PrintWriter(files[i], "UTF-8");
                writer.println(str);                
                writer.close();
               
                
            }
		
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
            
        
        }
	}
    //to remove extra spaces and lines from files
    public static void cleanfiles() throws UnsupportedEncodingException{
		
		File file = new File("inpu/");
		File[] files = file.listFiles();
		
		StringTokenizer st;
		
		for (int i = 0; i < files.length; i++) {
            //System.out.println(files[i]);
            try {

                Scanner sc = new Scanner(files[i]);
                String str = "";
                String temp = "";
              //  System.out.println(files[i].getName());
                while (sc.hasNextLine()) {
                	temp = sc.nextLine();
                    String[] array = temp.split(" ");
                    //System.out.println(array.length);
                    if (array.length==1){
                    	str+=temp;
                    	if(sc.hasNextLine()){
                    		temp = sc.nextLine();
                    	}
                    	str +=temp + "\n";
                    } else{
                    	str+=temp+"\n";
                    	
                    }                    
                }
                str.toLowerCase();
            //    System.out.println(str);
                sc.close();                
                PrintWriter writer = new PrintWriter(files[i], "UTF-8");
                writer.println(str);                
                writer.close();
                
            }
		
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
            
        
        }
		
	}
}
