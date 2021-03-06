package moura.renan.mobileui.bookmarked

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_bookmarked.*
import moura.renan.mobileui.R
import moura.renan.mobileui.injection.ViewModelFactory
import moura.renan.mobileui.mapper.ProjectViewMapper
import moura.renan.presentation.model.ProjectView
import moura.renan.presentation.state.Resource
import moura.renan.presentation.state.ResourceState
import moura.renan.presentation.viewmodel.BrowseBookmarkedProjectsViewModel
import javax.inject.Inject

class BookmarkedActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: BookmarkedAdapter
    @Inject
    lateinit var mapper: ProjectViewMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var browseViewModel: BrowseBookmarkedProjectsViewModel


    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, BookmarkedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarked)
        AndroidInjection.inject(this)

        browseViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(BrowseBookmarkedProjectsViewModel::class.java)
        setupBrowseRecylcer()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getBookmarkProjects().observe(this, Observer<Resource<List<ProjectView>>> { it ->
            it?.let {
                handleDataState(it)
            }
        })
        browseViewModel.fetchProjects()
    }

    private fun setupBrowseRecylcer() {
        recycler_projects.layoutManager = LinearLayoutManager(this)
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                progress.visibility = View.GONE
                recycler_projects.visibility = View.VISIBLE
                resource.data?.let {
                    adapter.projects = it.map { mapper.mapToView(it) }
                    adapter.notifyDataSetChanged()
                }
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_projects.visibility = View.GONE
            }
        }
    }
}