package intf;

// >> TODO 接口之间可以多继承。
// >> TODO 继承的接口可以有重复的方法，但是方法签名相同时，返回值必须完全一样，否则会有编译错误.
public interface intf3 extends intf1, intf2{
    void m3();
}
