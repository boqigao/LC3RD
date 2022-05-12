package daily;

public class LC134GasStation {
    /**
     * 这道题有两点需要理解，
     * 1. 如果从A站走不到的第一个站是B站，那么所有AB之间的站都走不到B站
     * 2. 如果gas的总和大于cost的总和，那么一定存在一个solution
     * 3. 因此从0开始试探，计算gas- cost的sum，然后碰到的和小于0的话就从下一站重新计数，标记start，
     */
    class SolutionWrong {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if (gas.length == 1) {
                return 0;
            }

            int curGas = gas[0];
            int left = 0, right = 1;

            while (left != right) {
                curGas = curGas - gas[right] + gas[right + 1];
                if (curGas < 0) {
                    left++;
                    curGas = curGas - gas[left] + gas[left + 1];
                } else {
                    right++;
                }
            }
            return 0;
        }
    }


    class SolutionRight {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            //determine if we have a solution
            int total = 0;
            for (int i = 0; i < gas.length; i++) {
                total += gas[i] - cost[i];
            }
            if (total < 0) {
                return -1;
            }

            // find out where to start
            int tank = 0;
            int start = 0;
            for (int i = 0; i < gas.length; i++) {
                tank += gas[i] - cost[i];
                if (tank < 0) {
                    start = i + 1;
                    tank = 0;
                }
            }
            return start;
        }
    }
}

