/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getctcfmotifs;

import java.io.*;
import java.util.*;
import java.lang.Math;

/**
 *
 * @author kanis_000
 */
public class GetCTCFMotifs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
        try{
            
            PrintWriter out= new PrintWriter(new FileWriter("out.txt"));
            System.out.println(System.getProperty("user.dir"));
            List<String> inputs= new <String>ArrayList();
            File newFile=new File("allcomp.txt");
            FileReader fileReader=new FileReader(newFile);
            BufferedReader reader=new BufferedReader(fileReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
             inputs.add(line);
            }
            
            File genomeFile=new File("chrY.fa");
            FileReader genomeFileReader=new FileReader(genomeFile);
            BufferedReader genomeReader=new BufferedReader(genomeFileReader);
            
            List<String> genomeInputs=new <String> ArrayList();
            String genomeLine=null;
            while ((genomeLine = genomeReader.readLine()) != null) {
             genomeInputs.add(genomeLine);
            }
            
            StringBuilder genome=new StringBuilder();
            
            for(int i=1;i<genomeInputs.size();i++){
                genome.append(genomeInputs.get(i));
            }
            
            List differenceInMotifPositions=new ArrayList();
            
            List <String> motifs=new <String> ArrayList();
            int minimumK=100000000;
            int lastPos=0;
            for(int i=1;i<inputs.size();i++){
                StringTokenizer row = new StringTokenizer(inputs.get(i),"\t");
                String id=row.nextToken();
                String species=row.nextToken();
                String chromosomeAndPosition=row.nextToken();
                StringTokenizer chromosomeData=new StringTokenizer(chromosomeAndPosition,":");
                String chromosomeNumber=chromosomeData.nextToken();
                String startAndEndPositions=chromosomeData.nextToken();
                if(chromosomeNumber.equals("chrY") && species.equals("Human")){
                    StringTokenizer positionData=new StringTokenizer(startAndEndPositions,"-");
                    int start=Integer.parseInt(positionData.nextToken());
                    int end=Integer.parseInt(positionData.nextToken());
                    int len=end-start;
                    differenceInMotifPositions.add(start-lastPos);
                    motifs.add(genome.substring(start,end));
                    if(len<minimumK)
                        minimumK=len;
                }
                
            }
            
            for(int i=0;i<differenceInMotifPositions.size();i++){
                out.print(differenceInMotifPositions.get(i));
                out.print("\t");
            }
            /*
            StringBuilder processedGenome=new StringBuilder();
            for(int i=0;i<genome.length();i++){
                if(genome.charAt(i)!='N'){
                    processedGenome.append(genome.charAt(i));
                }
            }
            
            out.println(processedGenome.toString().toUpperCase());
            
            out.println(minimumK);
            for(int i=0;i<motifs.size();i++){
                out.println(motifs.get(i).substring(0,minimumK).toUpperCase());
            }
            */
            
            out.close();
        
        }
                
        catch(Exception e)
        {
         e.printStackTrace();
        }
        
       
    }
    
}
