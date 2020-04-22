package mainStructures.unitStockpile;


import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class  StatusAltering {
    private final LinkedList<String> alteration = new LinkedList<>();
    private int cursor = 0;
    private final int sizeMax = 5;
    private boolean peek;
    CyDatabase status;

    public StatusAltering(CyDatabase status) {
        this.status=status;
        String wesh="abcdefghijklmnopqrstuvwxyz";
        Random random=new Random();
        StringBuilder filename = new StringBuilder();
        for (int j=0; j<sizeMax; j++){
            for(int i=0;i<6;i++){
                int number=random.nextInt(26);
                filename.append(wesh.charAt(number));
            }
            alteration.add(filename.toString());
            filename.delete(0,5);
        }
System.out.println(alteration);
        peek = false;
    }

    public void markStatus() {
        if (cursor==sizeMax-1){
            String tem = alteration.pollFirst();
            saveStatus(tem);
            alteration.add(tem);
        }else {
            saveStatus(alteration.get(cursor++));
            peek = false;
        }
        System.out.println(alteration);
    }

    public void saveStatus(String fileName) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName));
            stream.writeObject(status);
            stream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("48");
        }
        System.out.println(alteration);
    }
    public CyDatabase readStatus(String fileName) {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName));
            CyDatabase status = (CyDatabase) stream.readObject();
            stream.close();
            return status;
        } catch (ClassNotFoundException | IOException e) {
            System.err.println(e.getMessage());
            System.err.println("61");
        }
        return null;
    }


    public CyDatabase undoStatus() {
        if (cursor!=0)
            return readStatus(alteration.get(--cursor));
        else return null;
    }
    public CyDatabase redoStatus() {
        if (cursor<sizeMax)
            return readStatus(alteration.get(++cursor));
        else return null;
    }
}
