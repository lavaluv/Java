package byr.lavaluv.Algorithm;
/*
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * f(n) = f(n-1)+f(n-2)+...+f(n-(n-1)) + f(n-n) => f(0) + f(1) + f(2) + f(3) + ... + f(n-1) == f(n) = 2*f(n-1)
 * 本质为斐波那契数列
 */
public class JumpFloor {
    public int JumpFloorII(int target) {
        if(target <= 0){
            return 0;
        }
        else if(target == 1){
            return 1;
        }
        else if(target > 1){
            return 2*JumpFloorII(target - 1);
        }
        return -1;
    }
}
