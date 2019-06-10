package com.gupao.vip.student;



import java.io.*;

public class DataFile {

    private final static String DATA_EXT = ".db";
    private final static String KEY_EXT = ".idx";

    private String dataFilename ;
    private String keyFilename ;

    private RandomAccessFile db;
    private KeyFile keys;

    private DataFile(String failbase, boolean deleteFiles) throws FileNotFoundException {
        dataFilename = failbase + DATA_EXT;
        keyFilename = failbase + KEY_EXT;

        if (deleteFiles)
            deleteFile();
        openFiles();
    }

    private void openFiles() throws FileNotFoundException {
        keys = new KeyFile(keyFilename);
        db = new RandomAccessFile(new File(dataFilename),"rw");
    }

    public static DataFile open(String fileName) throws FileNotFoundException {
        return new DataFile(fileName,false);
    }

    public static DataFile create(String failbase) throws FileNotFoundException {
        return new DataFile(failbase,true);
    }

    public void add(String id, Object object) throws IOException {
        long position = db.length();

        byte[] bytes = getBytes(object);
        db.seek(position);
        db.write(bytes,0,bytes.length);

        keys.add(id,position,bytes.length);
    }

    private byte[] getBytes(Object object) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(object);
        outputStream.flush();
        return out.toByteArray();
    }

    public Object findById(String id) throws IOException {
        if (!keys.containsKey(id))
        return null;
        long position = keys.getPosition(id);
        db.seek(position);

        int length = keys.getLength(id);
        return read(length);
    }

    private Object read(int length) throws IOException {
        byte[] bytes = new byte[length];
        db.readFully(bytes);
        return readObject(bytes);
    }

    private Object readObject(byte[] bytes) throws IOException {
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));

        try {
            try {
                return in.readObject();
            }catch (ClassNotFoundException e){
                return null;
            }
        }finally {
            in.close();
        }

    }

    public void close() throws IOException {
        keys.close();
        db.close();
    }

    public void deleteFile() {
        IOUtil.delete(dataFilename,keyFilename);
    }

    public int size() {
        return keys.size();
    }
}
