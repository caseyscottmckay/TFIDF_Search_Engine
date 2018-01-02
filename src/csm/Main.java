package csm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;
 

public class Main {

    public static  ArrayList<WordVector> list = new ArrayList<WordVector>();
    public static ArrayList<String> list2= new ArrayList<String>();
    public static  ArrayList<Vocab> vocabList = new ArrayList<Vocab>();

    public static Map<String, Dictionary> hash  = new HashMap<String, Dictionary>();
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {

        //cleanfiles();
        //removeDuplicates();
        //getVocabFromFile();

      createSortedCollection();// call  above code only once to form sorted results
       //getTfIdf();
       // System.out.println(getIdf(19976, 20));
        //createCollectionFromFile();
       // createDictionaryionary();
        //getPostings("file");
        //getPostings("performance");
       // getPostings("read");
        //getPostings("window");
        //getPostings("subject");
       // t.getNumOfOccurrences();
    }

    public static void getDft(){

    }
    public static double getIdf(int totalNumDocs, int numDocsContainingWord){
        return Math.log10(totalNumDocs/numDocsContainingWord);
    }
    public static void  getTfIdf(){
       ArrayList<WordVector> temp = list;
        int termDocFreq;
        double tfidf=0;
        for (WordVector wordVector: temp) {
            tfidf = wordVector.getTermFreq() * getIdf(19766, 100);
            System.out.println(tfidf);
        }
    }



    public static void getPostings(String term) throws FileNotFoundException{
        if(!hash.containsKey(term)){
            System.out.println("Term not in the Dictionaryionary");
        } else{
            Dictionary dic = hash.get(term);
            System.out.println(term + " is in " + dic.DocFrecuency + " files");

            File file = new File(dic.PostingListPath);

            StringTokenizer st;

            Scanner sc = new Scanner(file);
            int docId = 0;
            int frequency = 0;
            System.out.println();
            System.out.println("DocId:\tFrequency:");
            //while (sc.hasNextLine()) {  // Use if we want to retrieve all elements.
            for (int f = 0 ; f<50 ; f++){
                st = new StringTokenizer(sc.nextLine());
                while(st.hasMoreTokens()){
                    docId = Integer.parseInt(st.nextToken());
                    frequency = Integer.parseInt(st.nextToken());
                    System.out.println(docId + "\t" + frequency);
                }
            }
            sc.close();
        }
    }


    public static void createDictionaryionary(){

        for(WordVector term : list){
            Dictionary temp = hash.get(term.term);
            String path;

            if(temp == null){
                temp = new Dictionary(term.termId, 1, "C:\\git\\dl4j-0.4-examples\\src\\main\\resources\\output" + term.term + ".res");
                path = temp.PostingListPath;
                hash.put(term.term, temp);
            } else{
                temp.DocFrecuency++;
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

        File file = new File("C:\\git\\dl4j-0.4-examples\\src\\main\\resources\\output\\sorted_wordVector");

        StringTokenizer st;


        Scanner sc = new Scanner(file);
        int docId = 0;
        //String temp = "";
        int frequency;
        int termId;

        while (sc.hasNextLine()) {
            st = new StringTokenizer(sc.nextLine());
            while(st.hasMoreTokens()){
               // temp = st.nextToken();
                termId=Integer.parseInt(st.nextToken());
                docId = Integer.parseInt(st.nextToken());
                frequency = Integer.parseInt(st.nextToken());
                list.add(new WordVector(termId, docId, frequency));
            }
        }
        sc.close();

    }

    //method to get vocabulary words from file

    public static void getVocabFromFile() throws FileNotFoundException, UnsupportedEncodingException{
    File vfile = new File("C:\\git\\dl4j-0.4-examples\\src\\main\\resources\\docs1");
            File[] files = vfile.listFiles();

            StringTokenizer st;

            for (int i = 0; i < files.length; i++) {
                Scanner sc = new Scanner(files[i]);
                String[] docIdTemp = files[i].getName().split("\\.");
//            int docId = Integer.parseInt(docIdTemp[0]);
                String temp = "";
                int k=0;
                while (sc.hasNextLine()) {
                    st = new StringTokenizer(sc.nextLine());
                    while(st.hasMoreTokens()){
                         temp = st.nextToken();
                        //termId=Integer.parseInt(st.nextToken());


                        vocabList.add(new Vocab(temp, ++k));
                    }
                }
                sc.close();

                PrintWriter writer = new PrintWriter("C:\\git\\dl4j-0.4-examples\\src\\main\\resources\\output\\sorted_vocab.txt", "UTF-8");
                for(int j = 0; j <vocabList.size(); j++){

                    writer.println(vocabList.get(j).toString());

                }
                writer.close();
            }
    }

    //method to be run only once to create the posting list files
    public static void createSortedCollection() throws FileNotFoundException, UnsupportedEncodingException {


        File file = new File("C:\\git\\dl4j-0.4-examples\\src\\main\\resources\\inputWV");
        File[] files = file.listFiles();

        StringTokenizer st;
        int k=0;
        for (int i = 0; i < files.length; i++) {
            Scanner sc = new Scanner(files[i]);
           // = files[i].getName().split("\\.");
//            int docId = Integer.parseInt(docIdTemp[0]);
            String docIdTemp;
            String[] docSet;
            String termIdTemp;
            String termFreqTemp;
            String temp = "";
            int termId;
            int docId;
            int frequency;
            int countLine=0;
            String line;
            while (sc.hasNextLine()) {
                st = new StringTokenizer(sc.nextLine());
                line= sc.nextLine();
                docSet= line.split(" ");
                while(st.hasMoreTokens()){
                    termIdTemp = docSet[0];
                    docIdTemp =docSet[1];
                    termFreqTemp=docSet[2];
                    //termId=Integer.parseInt(st.nextToken());
                   // docId =Integer.parseInt(st.nextToken());
                    //frequency = Integer.parseInt(st.nextToken());
                    //list.add(new WordVector(termId, docId, frequency));
                }
            }
            sc.close();


        }
       Collections.sort(list, new Comparator<WordVector>(){
            public int compare(WordVector t1, WordVector t2){
                int term1 = t1.termId;
                int term2 = t2.termId;
                //int termComp = term1.compareTo(t2.term);
                int result = new BigDecimal(t1.getTermId()).compareTo(new BigDecimal(t2.getTermId()));
                /*
                if(result < 0){
                    return -1;
                }
                if(result > 0){
                    return 1;
                } else{
                    if(result == 0){

                        if(t1.docId >= t2.docId){
                            result = 1;
                        } else{
                            result = -1;
                        }
                        return result;
                    }
                }*/
                return result;
            }});

        PrintWriter writer = new PrintWriter("C:\\git\\dl4j-0.4-examples\\src\\main\\resources\\output\\sorted_wordVector", "UTF-8");
        for(int j = 0; j <list.size(); j++){

            writer.println(list.get(j).toString());
           // System.out.println(list.get(j));

        }
        writer.close();

    }

    //used to delete the duplicates from the file
    /*public static void removeDuplicates() throws UnsupportedEncodingException{

        HashMap<Integer, Integer> checkDuplicates = new HashMap<Integer, Integer>();

        File file = new File("C:\\git\\dl4j-0.4-examples\\src\\main\\resources\\inputWordVector");
        File[] files = file.listFiles();

        StringTokenizer st;

        for (int i = 0; i < files.length; i++) {
            //System.out.println(files[i]);
            try {

                Scanner sc = new Scanner(files[i]);
                int temp;
                String str = "";
                //System.out.println(files[i]);
                while (sc.hasNextLine()) {
                    st = new StringTokenizer(sc.nextLine());
                    while(st.hasMoreTokens()){
                        temp = Integer.parseInt(st.nextToken());
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
    */
    //to remove extra spaces and lines from files
    public static void cleanfiles() throws UnsupportedEncodingException{

        File file = new File("C:\\git\\dl4j-0.4-examples\\src\\main\\resources\\inputWordVector");
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
