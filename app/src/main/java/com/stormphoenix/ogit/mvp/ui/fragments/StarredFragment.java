package com.stormphoenix.ogit.mvp.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.stormphoenix.httpknife.github.GitRepository;
import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.dagger2.component.DaggerActivityComponent;
import com.stormphoenix.ogit.dagger2.module.ContextModule;
import com.stormphoenix.ogit.mvp.presenter.StarredPresenter;
import com.stormphoenix.ogit.mvp.presenter.base.ListItemPresenter;
import com.stormphoenix.ogit.mvp.ui.fragments.base.ListFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by StormPhoenix on 17-2-27.
 * StormPhoenix is a intelligent Android developer.
 */
public class StarredFragment extends ListFragment<GitRepository> {
    @BindView(R.id.recy_starred_list)
    RecyclerView mRecyStarredList;

    @BindView(R.id.star_refresh_layout)
    SwipeRefreshLayout mStarRefreshLayout;

    @Inject
    StarredPresenter mRepositoryPresenter;

    public static StarredFragment getInstance(String username) {
        StarredFragment starredFragment = new StarredFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERNAME, username);
        starredFragment.setArguments(bundle);
        return starredFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_starred;
    }

    @Override
    public void initializeInjector() {
        DaggerActivityComponent.builder()
                .contextModule(new ContextModule(this.getActivity()))
                .build()
                .inject(this);
    }

    @Override
    public SwipeRefreshLayout getRefreshLayout() {
        return mStarRefreshLayout;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyStarredList;
    }

    @Override
    public ListItemPresenter getListItemPresetner() {
        return mRepositoryPresenter;
    }

    @Override
    public void initListItemView() {
        super.initListItemView();
        mAdapter.setOnViewClickListener(R.id.repository_card_wrapper, mRepositoryPresenter);
    }
}
