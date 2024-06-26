package com.leetcode.api.leetcode.baseThoughtImprove;

import java.io.IOException;
import java.util.Arrays;

/**
 * 二维数组的收集
 */
public class BaseArrayMatrix {

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     * 链接：https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
     *
     * 思路：
     * 设 l,r,t,b 左右上下，四个标识
     *
     *      打印方向     打印   打印后   是否结束
     * 1、从左至右打印    l->r   t+1      t < b
     * 2、从上至下打印    t->b   r-1      l < r
     * 3、从右至左打印    r->l   b-1      t < b
     * 4、从下至上打印    b->t   l+1      l < r
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[]{};

        int l = 0;
        int r = matrix[0].length - 1;
        int t = 0;
        int b = matrix.length - 1;
        int[] res = new int[matrix.length * matrix[0].length];
        int index = 0;

        //打印一圈为一次循环
        while (true){
            //1、从左至右打印
            for (int i = l; i <= r; i++) {
                res[index++] = matrix[t][i];
            }
            //判断是否结束
            if (++t > b) break;

            //2、从上至下打印
            for (int i = t; i <= b; i++) {
                res[index++] = matrix[i][r];
            }
            if (--r < l) break;

            //3、从右至左
            for (int i = r; i >= l; i--) {
                res[index++] = matrix[b][i];
            }
            if (--b < t) break;

            //4、从下至上
            for (int i = b; i >= t; i--) {
                res[index++] = matrix[i][l];
            }
            if (++l > r) break;
        }

        return res;
    }

    // 定义全局变量
    static int n, m, ans;
    // 定义二维数组
    static int[][] maze = new int[2005][2005];
    static int[][] vis = new int[2005][2005];
    // 定义向四个方向行走的数组
    static int[][] nxt = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     *Drizzle 前往山地统计大山的数目，现在收到这片区域的地图，地图中用0（平地）和1（山峰）绘制而成，请你帮忙计算其中的大山数目。
     *
     * 山总是被平地四面包围着，每一座山只能在水平或垂直方向上连接相邻的山峰而形成。一座山峰四面被平地包围，这个山峰也算一个大山。
     *
     * 另外，你可以假设地图的四面都被平地包围着。
     *
     * 输入格式:
     * 第一行输入M,N分别表示地图的行列，接下来M行每行输入N个数字表示地图。
     *
     *
     * 范围:
     *
     * 对于 5% 的数据：M，N ≤ 10
     * 对于 100% 的数据：M，N ≤ 2000
     *
     * 输出格式:
     * 输出一个整数表示大山的数目。
     *
     * 输入样例:
     * 在这里给出一组输入。例如：
     *
     * 4 5
     * 1 1 0 0 0
     * 1 1 0 0 0
     * 0 0 1 0 0
     * 0 0 0 1 1
     */
    public void nums() {
        // 遍历二维网格
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // 如果该点没有被访问过且值为1，则进行深度优先搜索
                if(vis[i][j] == 0 && maze[i][j] == 1){
                    vis[i][j] = 1;
                    dfs(i, j);
                    // 每找到一个连通块，答案加1
                    ans++;
                }
            }
        }
        // 输出连通块的数量
        System.out.println(ans);
    }

    // 定义深度优先搜索的方法
    static void dfs(int x, int y){
        // 对四个方向进行搜索
        for(int i = 0; i <= 3; i++){
            int nx = x + nxt[i][0];
            int ny = y + nxt[i][1];
            // 如果新的点在网格内，且没有被访问过，且值为1，则继续搜索
            if(nx >= 0 && nx <= n && ny >= 0 && ny <= m && vis[nx][ny] == 0 && maze[nx][ny] == 1){
                vis[nx][ny] = 1;
                dfs(nx, ny);
            }
        }
    }

    /**
     *
     * 48. 旋转图像
     * 已解答
     * 中等
     * 相关标签
     * 相关企业
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     *
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     *
     * https://leetcode.cn/problems/rotate-image/?envType=study-plan-v2&envId=top-interview-150
     *
     * 1   2  3  4
     * 5   6  7  8
     * 9  10 11 12
     * 13 14 15 16
     *
     * 13  9   5  1
     * 14  10  6  2
     * 15  11  7  3
     * 16  12  8  4
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //行1、列2，就已经都换了一遍了，除了5
        for (int row = 0; row < n / 2; row++) {
            for (int col = 0; col < (n + 1) / 2; col++) {
                //技巧：想着4X4的，换边就换row/col，换的意思是newRow根据col计算，newCol根据row计算；

                //顶部第二个[0,1](2)，暂存
                int tmp = matrix[row][col];
                //顶部第二个[0,1](2) = 左侧第三个[2,0](9)
                matrix[row][col] = matrix[n - col - 1][row];
                //左侧第三个[2,0](9) = 底部第三个[2,2](15)
                matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1];
                //底部第三个[2,2](15) = 右侧第二个[1,2](8)
                matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1];
                matrix[col][n - row - 1] = tmp;
            }
        }
    }

    /**
     * 73. 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     * https://leetcode.cn/problems/set-matrix-zeroes/description/?envType=study-plan-v2&envId=top-interview-150
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        // //方法一：空间复杂度O(m + n) 的情况去算
        // //1、记录每一行/列中是否有0，设为true
        // //2、遍历二维数组，判断改列、行是否有true；
        // if(matrix.length < 1 || matrix[0].length < 1) {
        //     return;
        // }
        // int rowLen = matrix.length;
        // int colLen = matrix[0].length;
        // boolean[] rowBol = new boolean[rowLen];
        // boolean[] colBol = new boolean[colLen];

        // for(int row = 0; row < rowLen; row ++) {
        //     for(int col = 0; col < colLen; col ++) {
        //         if(matrix[row][col] == 0) {
        //             rowBol[row] = colBol[col] = true;
        //         }
        //     }
        // }

        // for(int row = 0; row < rowLen; row ++) {
        //     for(int col = 0; col < colLen; col ++) {
        //         if(rowBol[row] || colBol[col]) {
        //              matrix[row][col] = 0;
        //         }
        //     }
        // }

        //方法二：空间复杂度O(1)
        //用第一行、第一列当成 boolean数组去记录；设置为0，他并不影响最后的结果，因为本身他也要被设置成0；
        if(matrix.length < 1 || matrix[0].length < 1) {
            return;
        }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        boolean rowFlag = false;
        boolean colFlag = false;
        //先判断下第一行、第一列中是否存在0；存在就在最后把整行、列设置0；
        for(int row = 0; row < rowLen; row ++) {
            if(matrix[row][0] == 0) {
                colFlag = true;
            }
        }
        for(int col = 0; col < colLen; col ++) {
            if(matrix[0][col] == 0) {
                rowFlag = true;
            }
        }
        //从1开始，第一行作为标记行
        for(int row = 1; row < rowLen; row ++) {
            for(int col = 1; col < colLen; col ++) {
                if(matrix[row][col] == 0) {
                    matrix[row][0] = matrix[0][col] = 0;
                }
            }
        }
        //根据第一行、列的标记为，设置所有位置
        for(int row = 1; row < rowLen; row ++) {
            for(int col = 1; col < colLen; col ++) {
                if(matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        if(rowFlag) {
            for(int col = 0; col < colLen; col ++) {
                matrix[0][col] = 0;
            }
        }
        if(colFlag) {
            for(int row = 0; row < rowLen; row ++) {
                matrix[row][0] = 0;
            }
        }
    }

    /**
     * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
     *
     * 给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
     *
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
     *
     * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/reshape-the-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        //自己想的
//        if(mat == null || mat.length < 1 || mat.length * mat[0].length != r * c) {
//            return mat;
//        }
//        int h = mat.length;
//        int l = mat[0].length;
//
//        int y = 0;
//        int x = 0;
//
//        int[][] arr = new int[r][c];
//
//        int ax = 0;
//        int ay = 0;
//        while(x < h && y < l) {
//            int v = mat[x][y ++];
//            if(ay < c) {
//                arr[ax][ay ++] = v;
//            }
//
//            if(ay == c) {
//                ay = 0;
//                ax ++;
//            }
//
//            if(y == l) {
//                x ++;
//                y = 0;
//            }
//
//        }
//        return arr;

        //参考的
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }

        int[][] ans = new int[r][c];
        for (int x = 0; x < m * n; ++x) {
            ans[x / c][x % c] = mat[x / n][x % n];
        }
        return ans;
    }

    /**
     * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     *  
     *
     * 注意：
     *
     * 一个有效的数独（部分已被填充）不一定是可解的。
     * 只需要根据以上规则，验证已经填入的数字是否有效即可。
     * 空白格用 '.' 表示。
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/valid-sudoku
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isValidSudoku(char[][] board) {
//        ArrayList<HashSet<Character>> rows = new ArrayList<HashSet<Character>>();
//        ArrayList<HashSet<Character>> columns = new ArrayList<>();
//        ArrayList<ArrayList<HashSet<Character>>> subbox = new ArrayList<>();
//        for (int i = 0; i < 9; i++) {
//            rows.add(new HashSet<Character>());
//            columns.add(new HashSet<Character>());
//        }
//        for (int i = 0; i < 3; i++) {
//            ArrayList<HashSet<Character>> hashSets = new ArrayList<>();
//            for (int j = 0; j < 3; j++) {
//                hashSets.add(new HashSet<Character>());
//            }
//            subbox.add(hashSets);
//        }
//
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                char v = board[i][j];
//                if (v != '.') {
//                    if (rows.get(i).contains(v) || columns.get(j).contains(v) || subbox.get(i / 3).get(j / 3).contains(v)) {
//                        return false;
//                    }
//                    rows.get(i).add(v);
//                    columns.get(j).add(v);
//                    subbox.get(i / 3).get(j / 3).add(v);
//                }
//            }
//        }
//        return true;


        // 因本题v 是1-9的数，所以可以直接用数组代替 hash
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    /**
     * 剑指 Offer 04. 二维数组中的查找
     * <p>
     * 在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，
     * 每一列都按照从上到下 非递减 的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 现有矩阵 matrix 如下：
     * <p>
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * <p>
     * 给定 target = 20，返回 false。
     * <p>
     * 链接：https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     * <p>
     * 思路一：暴力循环
     * 思路二：Hash 空间换时间
     * 思路三：每一行进行二分 O(n*log(m))
     * 思路四：Z字形查找
     * <p>
     * 如果 matrix[x,y]=target，说明搜索完成；
     * <p>
     * 如果 matrix[x,y]>target，由于每一列的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 y 列的元素都是严格大于 target 的，因此我们可以将它们全部忽略，即将 y 减少 1；
     * <p>
     * 如果 matrix[x,y]<target，由于每一行的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 x 行的元素都是严格小于 target 的，因此我们可以将它们全部忽略，即将 x 增加 1。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

//        //二分
//        for (int i = 0; i < matrix.length; i++) {
//            if (findBinarySearch(matrix[i],target)){
//                return true;
//            }
//        }
//        return false;

        //Z字形
        if (matrix.length < 1) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int x = 0;
        int y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                y--;
            } else if (matrix[x][y] < target) {
                x++;
            }
        }
        return false;

    }

    /**
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     *  
     *
     * 示例 1:
     *
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 12
     * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
     *  
     *
     * 提示：
     *
     * 0 < grid.length <= 200
     * 0 < grid[0].length <= 200
     *
     *
     * 链接：https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof
     *
     * 思路：
     *
     * 一、推出公式
     *      每次在矩阵中，只能向左或者向下移动，所以
     *      当grid(1,1)也就是5的值设为f(1,1)，选取最佳路径时，应该比较max(f(0,1), f(1,0))两个路径上的最大值 + grid(1,1)
     *
     *      那么我们可以大概推出动态规划方程：
     *      f(i,j) = max(f(i - 1,j), f(i, j -1)) + grid(i,j)
     *
     *      但是需要特殊处理下i = 0;j = 0时的特殊情况。
     *          1、i = 0 , j = 0; f(i,j) = grid(0,0)
     *          2、i = 0 , j != 0: f(i,j) = f(i, j -1) + grid(i,j)
     *          3、i != 0 , j = 9; f(i,j) = f(i - 1, j) + grid(i,j)
     *          4、i,j != 0; f(i,j) = max(f(i - 1,j), f(i, j -1)) + grid(i,j)
     *
     * 二、优化空间复杂度
     *      若我们把每个f(i,j)的值都存下来，需要o(N*M)，所以我们可以直接原地修改值
     *
     */
    public int maxValue(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.max(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
                }
            }
        }

        return grid[n-1][m-1];

    }

    public static void main(String[] args) throws IOException {
        int k = 7;
        // 从标准输入流中读取一行数据，转换为整形数值k
        //0, 1, 4
        int[] nCount = new int[k];
        // 初始化一个长度为k的数组，用于存储下标为n时，数据=[0 + (0+1) + ~ + (0+1+ ~ +n)]的值

        boolean flag = false;
        // 初始化一个标志位，表示是否找到了符合条件的一组或多组连续正整数

        for (int start = 1; start < k; start++) {
            for (int len = 1; len < k; len++) {
                int currentK = calK(start, len, nCount);
                // 计算当前的currentK值

                if (currentK == k) {
                    if (flag) System.out.print("\n");
                    printResult(start, len, nCount);
                    // 如果currentK等于k，打印满足条件的连续正整数序列，并将标志位设为true

                    flag = true;
                    break;
                } else if (currentK > k) {
                    break;
                    // 如果currentK大于k，则终止内层循环
                }
            }
            System.out.println(Arrays.toString(nCount));
        }

        if(!flag) {
            System.out.println("");
            // 如果没有找到满足条件的连续正整数序列，打印空行
        }
    }

    public static int calK(int x, int n, int[] nCount) {
        if(nCount[n] == 0) {
            int tmp = 0;
            for(int i=0; i<=n; i++) tmp=tmp+i;
            nCount[n] = nCount[n-1] + tmp;
            // 如果nCount[n]的值为0，计算它的值并存储
        }
        return x*(n+1) + nCount[n];
        // 返回计算后的currentK值
    }

    public static void printResult(int x, int n, int[] nCount) {
        StringBuilder sb = new StringBuilder();
        int tmp=x;
        for(int i=0; i<=n; i++) {
            tmp = tmp +i;
            sb.append(tmp);
            if(i==n) break;
            sb.append(",");
            // 拼接满足条件的连续正整数序列
        }
        System.out.print(sb);
        // 打印满足条件的连续正整数序列
    }

//    public static void main(String[] args) {
////        TreeNode node = new TreeNode(1);
////        TreeNode right = new TreeNode(2);
////        node.left = null;
////        node.right = right;
////        System.out.println(node.val + ' ' + (node.left != null ? node.left.val : 'null') + ' ' + node.right.val);
////
////        mirrorTree(node);
////        System.out.println(node.val + ' ' + (node.left != null ? node.left.val : 'null') + ' ' + (node.right != null ? node.right.val : 'null'));
//
//
////        char[][] arr = new char[][]{
////                {'A', 'B', 'C', 'C'},
////                {'S', 'F', 'C', 'S'},
////                {'A', 'D', 'E', 'E'}
////        };
////
////        System.out.println(exist1(arr, "ABCCED") ? "true" : "false");
//
////        System.out.println(Boolean.FALSE == false);
//
//        List<Integer> list = new ArrayList<>();
//        list.add(5);
//        list.add(3);
//        list.add(1);
//
//        list.add(6);
//        list.add(0,4);
//        list.remove(1);
//        System.out.println(Arrays.toString(list.toArray()));
//
//        HashMap<Character, Integer> map = new HashMap();
//        for (Map.Entry<Character,Integer> entry:
//             map.entrySet()
//        ) {
//        }
//
//    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * <p>
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     *  {'A', 'B', 'C', 'C'},
     *  {'S', 'F', 'C', 'S'},
     *  {'A', 'D', 'E', 'E'}
     *
     *  ABCCED
     *
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * <p>
     * 链接：https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof
     *
     *
     * 思路：递归
     */
    public static boolean exist1(char[][] board, String word) {
        int r = board.length;
        int l = board[0].length;

        boolean[][] visited = new boolean[r][l];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < l; j++) {
                if (check1(board, visited, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean check1(char[][] board, boolean[][] visited, String word, int r, int l, int k) {
        if (board[r][l] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }

        int[][] range = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        visited[r][l] = true;
        for (int i = 0; i < range.length; i++) {
            int newr = r + range[i][0];
            int newl = l + range[i][1];
            if (newr >= 0 && newr < board.length && newl >= 0 && newl < board[0].length) {
                if (!visited[newr][newl]) {
                    if (check1(board, visited, word, newr, newl, k + 1)) {
                        return true;
                    }
                }
            }
        }
        visited[r][l] = false;
        return false;
    }


    /**
     * 面试题13. 机器人的运动范围
     *
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     *
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     *
     * 示例 2：
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     *
     * 提示：
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     *
     * 链接：https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
     *
     *
     * 思路一：递归算法
     * todo 思路二：广度优先算法：https://leetcode.cn/tag/breadth-first-search/problemset/
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }


    public int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
        if (i >= m || j >= n || k < getSum(i) + getSum(j) || visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;
        return 1 + dfs(i + 1, j, m, n, k, visited) + dfs(i, j + 1, m, n, k, visited);
    }

    public int getSum(int num) {
        return num % 10 + num / 10;
    }


}
