import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
    }

    open fun setContentLayout() {
        setContentView(layoutRes)
        initView()
        initData()
    }

    @get:LayoutRes
    abstract val layoutRes: Int

    abstract fun initView()

    open fun initData() {

    }
}