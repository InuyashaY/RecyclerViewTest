package yzl.swu.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.*
import yzl.swu.recyclerviewtest.adapter.MainAdapter

import yzl.swu.recyclerviewtest.databinding.ActivityMainBinding


/**
 * RecyclerView
 *  -adapter   完成数据的显示  Recycler View.Adapter
 *  -layoutManager  样式 ： 线性 网格  瀑布流     设置布局方向
 *  -itemDecoration 每一个item的装饰器
 *  -xxx.xml    每一个item显示的样子
 *
 *
 *  使用步骤：
 *      1、创建RecyclerView    通常在xml中配置
 *      2、代码中设置属性
 *          确定布局样式  设置layoutManager
 *          确定数据源
 *     3、定义一个Adapter实现RecyclerView.Adapter接口中的方法
 *          RecyclerView通过接口中的方法访问数据
 *          a、创建一个类继承于 RecyclerView.ViewHolder
 *              重复利用
 *          b、重写adapter的接口方法
 *              getItemCount：确定RecyclerView中有多少个item
 *              onCreateViewHolder：确定每个item的具体视图
 *              onBindViewHolder：绑定视图
 *     4、设置item装饰器      addItemDecoration
 *          1）系统提供的 DividerItemDecoration 分割线
 *          2）⾃⼰创建⼀个类继承于ItemDecoration 重写onDraw或者onDrawOver、getItemOffset
 *     5. 如果每个Item显示⼀屏 按⻚来显示    SnapHelper  LinearSnapHelper    PagerSnapHelper
 *     6. CardView的使⽤
 * */
class MainActivity : AppCompatActivity() {
    /** AS4.1版本不默认支持用id访问控件     这里使用ViewBinding
     * 如果想要继续使用id访问控件  模块的build.gradle中加入  id 'kotlin-android-extensions'
     * 这里默认生成了ActivityMainBinding的类，此类关联了activity_main.xml
     */
    private var mBinding: ActivityMainBinding? = null
    private val dataSource:ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //绑定xml
        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        //设置为xml的根容器    即ConstraintLayout
        setContentView(mBinding?.root)

        //设置布局样式
        mBinding?.mRecyclerView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        mBinding?.mRecyclerView?.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                //确定数据源
        for (i in 1..10){
            if (i % 2 == 0){
                dataSource.add(R.drawable.sy)
            }else{
                dataSource.add(R.drawable.gl)
            }
        }

        //设置适配器
        mBinding?.mRecyclerView?.adapter = MainAdapter(dataSource)

        //辅助类   使得滑动如翻页
        PagerSnapHelper().attachToRecyclerView(mBinding?.mRecyclerView)

        //设置装饰decoration 间隔、分割线等
        mBinding?.mRecyclerView?.addItemDecoration(MyItemDecoration())
    }
}