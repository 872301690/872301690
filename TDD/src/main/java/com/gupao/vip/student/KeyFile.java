package com.gupao.vip.student;

import java.util.*;
import java.io.*;
public class KeyFile {

    private Map<String,EntryData> map = new HashMap<>();
    private File file ;
    public KeyFile(String keyFilename) throws IOException {
        file = new File(keyFilename);
        if(file.exists())
            load();
    }

    private void load() throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        try {
            try {
                map = (Map<String, EntryData>) in.readObject();
            } catch (ClassNotFoundException e) {

            }
        }finally {
            in.close();
        }

    }

    public void close() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(map);
        out.flush();
        out.close();
    }


    public int size() {
        return map.size();
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public long getPosition(String key) {
        EntryData entryData = map.get(key);
        if(entryData == null) return 0;
        return entryData.getPosition();
    }

    public int getLength(String key) {
        EntryData entryData = map.get(key);
        if(entryData == null) return 0;
        return entryData.getLengthl();
    }

    public void add(String id, long position, int length) {
        map.put(id,new EntryData(position,length));
    }

    static class EntryData {
        private long position;
        private int lengthl;

        public EntryData(long position, int lengthl) {
            this.position = position;
            this.lengthl = lengthl;
        }

        public int getLengthl() {
            return lengthl;
        }

        public long getPosition() {

            return position;
        }
    }
}
