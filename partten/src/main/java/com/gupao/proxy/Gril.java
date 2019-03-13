package com.gupao.proxy;


public class Gril implements Person {
    @Override
    public void findLove() {
        System.out.println("高富帅");
    }

    public static void main(String[] args) throws Exception {
        GPMeiPo meiPo = new GPMeiPo();
        Person proxy = (Person) meiPo.getProxy(new Gril());
        proxy.findLove();
    }
}
