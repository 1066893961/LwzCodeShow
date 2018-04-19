package com.xiaweizi.materialdesign.javatest;

/**
 * Created by lwz on 2017/4/28.
 * 项目详情页面
 */

public class NewProjectDetailsActivity {
//        extends BaseActivity implements MyItemClickListener,
//        BGARefreshLayout.BGARefreshLayoutDelegate, View.OnLayoutChangeListener,
//        View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener {

/*
    @BindView(R.id.project_comment_recycleview)
    RecyclerView projectCommentRecycleview;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.share_ll)
    LinearLayout shareLl;
    @BindView(R.id.keyboard_ll)
    LinearLayout keyboardLl;
    @BindView(R.id.connect_agent_ll)
    LinearLayout connectAgentLl;
    @BindView(R.id.comment_bottom_ll)
    LinearLayout commentBottomLl;
    @BindView(R.id.project_details_fl)
    FrameLayout projectDetailsFl;
    @BindView(R.id.brand_collection_img)
    ImageView brandCollectionImg;

    private BetterRecyclerView productRecyclerviewHorizontal, videoRecyclerviewHorizontal,
            real_store_recycleview, mFooterRey;
    private RecyclerView mSelectorRey;
    private JCVideoPlayerStandardNoTitle project_video;
    private TextView project_details_title_tv, project_comment_total_numm_tv;
    private LinearLayout project_auth_ll, project_collection_ll, mFooterShowLl, mFooterHideLl,
            mQuestionAreaLl, mSelectorAreaLl, mCommentAreaLl, mSelectLl, mFounderLl,
            mOtherBrandRecommendLl, mRootLineLl;
    private FixGridLayout mFounderTagLl;
    private RelativeLayout mCommentRel;
    private TextView empty_tv, mFounderNameTv, mFounderIntroductionTv, mFounderDescribeTv;//暂无评论
    private ImageView project_collection_img;
    private CustomCircleImageView mFounderHeaderImg;
    private int screenWidth;
    private ProjectCommentAdapter mProjectCommentAdapter;
    private SelectorAdapter mSelectorAdapter;
    private BrandYlfxAdapter brandYlfxAdapter;//盈利分析adapter
    private ProjectDetailsBean projectDetailsBean;
    private ProjectDetailsBean.PhoneCommentBean phoneCommentBean;

    private int totalPage = 1;
    private final static String TITLE = "TITLE";
    private final static String BRANDID = "BRANDID";
    private String projectTitle;
    private String brandId;
    private String logo;
    private int keyHeight = 0;
    private int mMorePageNumber = 2;
    private PopupInputWindow mInputWindow;
    private String qno;//客服队列号
    private EmptyView mEmptyView;
    private String mShareUrl;
    private boolean isNeedPauseVideo = false;

    *//**
     * 横向recycleView相关
     *//*
    private ProjectOutdoorSceneAdapter mOutDoorSceneAdapter;
    private ProjectProductImgAdapter mProductImgAdapter;
    private ProjectVideoAdapter mVideoAdapter;
    private ProjectFooterAdapter mProjectFooterAdapter;
    private ImagPagerUtil imagPagerUtil;
    private List<ProjectDetailsBean.PhoneCommentBean.ListBeanXXXX> commentListBeen = new ArrayList<>();
    private List<ProjectDetailsBean.PhoneCommentBean.ListBeanXXXX> commentList = new ArrayList<>();
    private List<ProjectDetailsBean.AskBean> askBeans = new ArrayList<>();
    private ArrayList<String> strList = new ArrayList<>();
    private ArrayList<String> imageList = new ArrayList<>();

    *//**
     * 新版更改相关
     *//*
    private RecyclerView brand_ylfx_lv;//盈利分析
    private ImageView brand_attestation_img;//认证图标
    private TextView project_auth_tv;//认证文字
    private TextView guanzhu_tv;//关注文字
    private TextView brand_all_investment_tv, attention_number_tv,
            shop_area_tv, main_product_tv, contract_period_tv, join_money_tv;
    private LinearLayout real_store_ll, investment_computer_rel, join_area_ll;
    private LinearLayout mBottomLl;
    private CardView mTopLl;
    *//**
     * 各个大模块布局   如果没有全部隐藏
     *//*
    private LinearLayout brand_hbsk_ll, brand_ppys_ll, brand_ylfx_ll;
    private ArrayList<String> ylfxList = new ArrayList<>();
    private Handler mHander;
    private BGABanner brand_detail_top_banner;

    public static void start(Context context, String name, String brandId) {
        Intent intent = new Intent();
        intent.setClass(context, NewProjectDetailsActivity.class);
        intent.putExtra(TITLE, name);
        intent.putExtra(BRANDID, brandId);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_details;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return projectTitle;
    }

    @Override
    protected View getRightView() {
        ImageView imageView = ViewUtils.getRightImageView(mActivity, R.mipmap.icon_erji);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopDialogUtils.getInstance().showBrandCallDialog(NewProjectDetailsActivity.this, new PopDialogUtils.OnSureClickListener() {
                    @Override
                    public void onSure() {
                        if (StringUtils.isEmpty(qno)) {
                            getQueueByBrandId();
                        } else {
                            //拨打电话埋点
                            JSONObject object = new JSONObject();
                            try {
                                object.put("name", projectTitle);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            GrowingIO.getInstance().track("brand_detail_phone", object);
                            CallPhoneManager.callPhonePhone(NewProjectDetailsActivity.this, qno);
                        }
                    }

                    @Override
                    public void onCancel() {
                        if (Preferences.isLogin()) {
                            //在线咨询埋点
                            JSONObject object = new JSONObject();
                            try {
                                object.put("name", "");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            GrowingIO.getInstance().track("brand_detail_online", object);
                            if (projectDetailsBean != null) {
                                getImAcc();
                            }
                        } else {
                            LoginActivity.start(NewProjectDetailsActivity.this);
                        }
                    }
                });
            }
        });
        return imageView;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        projectTitle = getIntent().getStringExtra(TITLE);
        brandId = getIntent().getStringExtra(BRANDID);
        keyHeight = ScreenUtils.getScreenHeight(getApplicationContext()) / 3;
        mHander = new Handler();
        getWindow().setBackgroundDrawable(null);
    }


    *//**
     * 评论列表
     *//*
    private void doCommentLogic() {
        if (projectDetailsBean.getPhoneComment().getList() != null && projectDetailsBean.getPhoneComment().getList().size() != 0) {
            commentListBeen = projectDetailsBean.getPhoneComment().getList();
            if (commentListBeen.size() >= 1) {
                mProjectCommentAdapter.setTimeTvShow(false);
                if (commentList.size() != 0) {
                    commentList.clear();
                }
                commentList.add(commentListBeen.get(0));
                mProjectCommentAdapter.setLastViewShow(false);
                mProjectCommentAdapter.setNewData(commentList);
                mProjectCommentAdapter.setEnableLoadMore(false);
                mFooterShowLl.setVisibility(View.VISIBLE);
                mFooterHideLl.setVisibility(View.GONE);
            }

        } else {//没有评论全部隐藏
//            mOtherBrandRecommendLl.setVisibility(View.GONE);
            empty_tv.setVisibility(View.VISIBLE);
            mRootLineLl.setVisibility(View.VISIBLE);
//            mCommentRel.setVisibility(View.GONE);
            mProjectCommentAdapter.isUseEmpty(false);
            mProjectCommentAdapter.setHeaderAndEmpty(true);
            mEmptyView.setViewState(EmptyView.ViewState.GONE);
        }
    }

    *//**
     * 火爆实况
     *//*
    private void doHbsKLogic() {
        if (projectDetailsBean.getVideo().getList() != null && projectDetailsBean.getVideo().getList().size() != 0) {
            mVideoAdapter.setData(projectDetailsBean.getVideo().getList(), projectDetailsBean.getBrandId(), (projectDetailsBean.getVideo().getTotal() - 1) / GlobalKeyContans.PAGE_SIZE + 1);
        } else {
            brand_hbsk_ll.setVisibility(View.GONE);
        }
    }

    *//**
     * 品牌优势
     *//*
    private void doBrandAdvangeLogic() {
        if (projectDetailsBean.getAdvantageImg().getList() != null && projectDetailsBean.getAdvantageImg().getList().size() != 0) {
            brand_ppys_ll.setVisibility(View.VISIBLE);
            mProductImgAdapter.setData(projectDetailsBean.getAdvantageImg().getList(), projectDetailsBean.getBrandId(), (projectDetailsBean.getAdvantageImg().getTotal() - 1) / GlobalKeyContans.PAGE_SIZE + 1);
        } else {
            brand_ppys_ll.setVisibility(View.GONE);
        }
    }

    *//**
     * 门店实景
     *//*
    private void doRealStoreLogic() {
        if (projectDetailsBean.getOutdoorScene().getList() != null && projectDetailsBean.getOutdoorScene().getList().size() != 0) {
            real_store_ll.setVisibility(View.VISIBLE);
            mOutDoorSceneAdapter.setData(projectDetailsBean.getOutdoorScene().getList(), projectDetailsBean.getBrandId(), (projectDetailsBean.getOutdoorScene().getTotal() - 1) / GlobalKeyContans.PAGE_SIZE + 1);
        } else {
            real_store_ll.setVisibility(View.GONE);
        }
    }

    *//**
     * 其他项目推荐
     *//*
    private void doBrandRecommentLogic() {
        if (projectDetailsBean.getBrandRecommendRsp() != null) {
            if (projectDetailsBean.getBrandRecommendRsp().getList() != null && projectDetailsBean.getBrandRecommendRsp().getList().size() != 0) {
                mOtherBrandRecommendLl.setVisibility(View.VISIBLE);
                mProjectFooterAdapter.setData(projectDetailsBean.getBrandRecommendRsp().getList(), projectDetailsBean.getBrandId(), projectDetailsBean.getBrandRecommendRsp().getPages());
            }
        } else {
            mOtherBrandRecommendLl.setVisibility(View.GONE);
        }
    }

    *//**
     * 处理创始人信息
     *//*
    private void doFounderLogic(List<ProjectDetailsBean.PhoneCommentBean.ListBeanXXXX> list) {
        if (projectDetailsBean.getFounderRsp() != null) {
            ImageLoader.loadNoCenterBrand(getApplicationContext(), projectDetailsBean.getFounderRsp().getHeadUrl(),
                    mFounderHeaderImg, R.mipmap.icon_default);
            mFounderNameTv.setText(projectDetailsBean.getFounderRsp().getFounderName());
            if (projectDetailsBean.getFounderRsp().getFounderTag() != null && projectDetailsBean.getFounderRsp().getFounderTag().size() != 0) {
                mFounderTagLl.removeAllViews();
                int size = projectDetailsBean.getFounderRsp().getFounderTag().size();
                for (int i = 0; i < size; i++) {
                    TextView textView = new TextView(getApplicationContext());
                    textView.setTextColor(getApplicationContext().getResources().getColor(R.color.color_999999));
                    textView.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.brand_tv_tag_bg));
                    textView.setTextSize(10);
                    textView.setMinWidth(DipUtils.dip2px(getApplicationContext(), 25));
                    textView.setPadding(DipUtils.dip2px(getApplicationContext(), 4),
                            DipUtils.dip2px(getApplicationContext(), 4),
                            DipUtils.dip2px(getApplicationContext(), 4),
                            DipUtils.dip2px(getApplicationContext(), 4));
                    textView.setGravity(Gravity.CENTER);
                    textView.setText(projectDetailsBean.getFounderRsp().getFounderTag().get(i));
                    mFounderTagLl.addView(textView);
                }
            }
            mFounderIntroductionTv.setText(projectDetailsBean.getFounderRsp().getIntroduction());
            mFounderDescribeTv.setText(projectDetailsBean.getFounderRsp().getBewrite());
        } else {
            mFounderLl.setVisibility(View.GONE);
        }
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        mFounderLl.post(new Runnable() {
            @Override
            public void run() {
                int h = mTopLl.getHeight();
                int w = mTopLl.getWidth();
                int h2 = mBottomLl.getHeight();

                if (h > h2) {
                    params.height = h;
                    params.width = w;
                    params.setMargins(DipUtils.dp2px(getApplicationContext(), 22),
                            DipUtils.dp2px(getApplicationContext(), 20), 0,
                            DipUtils.dp2px(getApplicationContext(), 20));
                    mTopLl.setLayoutParams(params);
                    params2.height = h - DipUtils.dp2px(getApplicationContext(), 30);
                    params2.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    params2.setMargins(DipUtils.dp2px(getApplicationContext(), 12),
                            DipUtils.dp2px(getApplicationContext(), 35),
                            DipUtils.dp2px(getApplicationContext(), 12),
                            DipUtils.dp2px(getApplicationContext(), 35));
                    mBottomLl.setLayoutParams(params2);

                } else {
                    params.height = h2 + DipUtils.dp2px(getApplicationContext(), 30);
                    params.width = w;
                    params.setMargins(DipUtils.dp2px(getApplicationContext(), 22),
                            DipUtils.dp2px(getApplicationContext(), 20), 0,
                            DipUtils.dp2px(getApplicationContext(), 20));
                    mTopLl.setLayoutParams(params);
                    params2.height = h2;
                    params2.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    params2.setMargins(DipUtils.dp2px(getApplicationContext(), 22),
                            DipUtils.dp2px(getApplicationContext(), 35),
                            DipUtils.dp2px(getApplicationContext(), 12),
                            DipUtils.dp2px(getApplicationContext(), 35));
                    mBottomLl.setLayoutParams(params2);
                }
            }
        });
    }

    *//**
     * 处理盈利分析逻辑
     *//*

    private void doYlfxLogic() {
        if (projectDetailsBean.getTableAnalysis() != null) {
            if (ylfxList != null && ylfxList.size() != 0) {
                ylfxList.clear();
            }
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    ylfxList.add(StringUtils.isEmpty(projectDetailsBean.getInvesmentAmount()) ? "--" : projectDetailsBean.getInvesmentAmount());
                } else if (i == 1) {
                    ylfxList.add(StringUtils.isEmpty(projectDetailsBean.getTableAnalysis().getSingle()) ? "--" : projectDetailsBean.getTableAnalysis().getSingle() + "元/人");
                } else if (i == 2) {
                    ylfxList.add(StringUtils.isEmpty(projectDetailsBean.getTableAnalysis().getDailyFlow()) ? "--" : projectDetailsBean.getTableAnalysis().getDailyFlow() + "人/日");
                } else if (i == 3) {
                    ylfxList.add(StringUtils.isEmpty(projectDetailsBean.getTableAnalysis().getMonthlySales()) ? "--" : projectDetailsBean.getTableAnalysis().getMonthlySales() + "万元");
                } else if (i == 4) {
                    ylfxList.add(StringUtils.isEmpty(projectDetailsBean.getTableAnalysis().getGrossProfit()) ? "--" : projectDetailsBean.getTableAnalysis().getGrossProfit() + "%");
                } else if (i == 5) {
                    ylfxList.add(projectDetailsBean.getReturnCycleMin() + "~" + projectDetailsBean.getReturnCycleMax() + "个月");
                }
            }
            brand_ylfx_ll.setVisibility(View.VISIBLE);
            brandYlfxAdapter.setData(ylfxList);
        } else {
            brand_ylfx_ll.setVisibility(View.GONE);
        }
    }

    *//**
     * 处理是否关注  是否认证逻辑
     *//*
    private void doIsAttentionOrIsAttestation() {
        if (projectDetailsBean.isAttention()) {
            project_collection_img.setImageResource(R.mipmap.icon_attention_hover);
            brandCollectionImg.setImageResource(R.mipmap.icon_follow_hover);
            guanzhu_tv.setText("已关注");
            guanzhu_tv.setTextColor(getResources().getColor(R.color.color_279683));
        } else {
            project_collection_img.setImageResource(R.mipmap.icon_guanzhu_normal);
            brandCollectionImg.setImageResource(R.mipmap.icon_follow);
            guanzhu_tv.setText("关注");
            guanzhu_tv.setTextColor(getResources().getColor(R.color.color_666666));
        }
        if (beanIsEmpty(1)) {
            brand_attestation_img.setImageResource(R.mipmap.icon_attestation_normal);
            project_auth_tv.setText("认证");
            project_auth_tv.setTextColor(getResources().getColor(R.color.color_666666));
        } else {
            brand_attestation_img.setImageResource(R.mipmap.icon_attestation_hover);
            project_auth_tv.setText("已认证");
            project_auth_tv.setTextColor(getResources().getColor(R.color.color_279683));
        }
    }

    *//**
     * 设置品牌简介文字
     *//*
    private void setBrandIntroductionText() {
        brand_all_investment_tv.setText(projectDetailsBean.getInvesmentAmount());
        join_money_tv.setText(projectDetailsBean.getJoinInvestMin() + "~" + projectDetailsBean.getJoinInvestMax() + "万");
        contract_period_tv.setText(projectDetailsBean.getContractPeriod() + "年");
        attention_number_tv.setText(projectDetailsBean.getFocusCount() + "人");
        main_product_tv.setText(projectDetailsBean.getBrandIndustry());
        if (projectDetailsBean.getMainProductList().size() != 0) {
            int size = projectDetailsBean.getMainProductList().size();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                list.add(projectDetailsBean.getMainProductList().get(i));
            }
            main_product_tv.setText(StringUtils.listToString(list));
        } else {
            main_product_tv.setText("");
        }
        shop_area_tv.setText(projectDetailsBean.getJoinRequire());

    }

    @Override
    protected void onResume() {
        super.onResume();
        ShareManager.getInstance().onResume();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserEvent event) {
        if (event.getState() == GlobalKeyContans.EVENT_KEY_LOGIN) {
            getBrandList();
        } else if (event.getState() == GlobalKeyContans.EVENT_KEY_KICKOUT) {
            getBrandList();
            isNeedPauseVideo = true;
            if (project_video.currentScreen == JCVideoPlayer.SCREEN_LAYOUT_NORMAL) {
                JCVideoPlayer.backPress();
            }
            if (project_video.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                project_video.startButton.performClick();
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (isNeedPauseVideo) {
            isNeedPauseVideo = false;
        } else {
            JCVideoPlayer.releaseAllVideos();
        }
        LogUtil.i(TAG, "onPause");
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(TAG, "onStop");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        *//**
         * 评论recycleview
         *//*
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(mActivity, true));
        mRefreshLayout.setPullDownRefreshEnable(false);

        mProjectCommentAdapter = new ProjectCommentAdapter(R.layout.project_comment_list_item, commentListBeen);
        mEmptyView = ViewUtils.getEmptyView(mActivity);
        mProjectCommentAdapter.setEmptyView(mEmptyView);
//        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mProjectCommentAdapter.setOnLoadMoreListener(this);
        // 添加 HeaderView
        mProjectCommentAdapter.addHeaderView(getHeaderView());
        mProjectCommentAdapter.addFooterView(getFooterView());
        projectCommentRecycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mProjectCommentAdapter.setLoadMoreView(ViewUtils.getLoadMoreView());
        projectCommentRecycleview.setAdapter(mProjectCommentAdapter);

        mEmptyView.setOnErrorClickListener(new EmptyView.OnErrorClickListener() {
            @Override
            public void onClick() {
                mEmptyView.setViewState(EmptyView.ViewState.LODDING);
                getBrandList();
            }
        });

        //精选问答
        mSelectorAdapter = new SelectorAdapter(R.layout.selector_rey_item, askBeans);
        mSelectorRey.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSelectorRey.setAdapter(mSelectorAdapter);
        *//**
         * 横向recycleview  产品图
         *//*
        productRecyclerviewHorizontal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //设置适配器
        mProductImgAdapter = new ProjectProductImgAdapter(getApplicationContext(), screenWidth, 2);
        mProductImgAdapter.setFootViewText("加载中");
        productRecyclerviewHorizontal.setAdapter(mProductImgAdapter);
        mProductImgAdapter.setOnItemClickListener(this);


        *//**
         * 横向recycleview  门店实景
         *//*
        real_store_recycleview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //设置适配器
        mOutDoorSceneAdapter = new ProjectOutdoorSceneAdapter(NewProjectDetailsActivity.this, screenWidth, 5);
        mOutDoorSceneAdapter.setFootViewText("加载中");
        real_store_recycleview.setAdapter(mOutDoorSceneAdapter);
        mOutDoorSceneAdapter.setOnItemClickListener(this);

        *//**
         * 横向底部项目推荐品牌
         *//*
        mFooterRey.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mProjectFooterAdapter = new ProjectFooterAdapter(NewProjectDetailsActivity.this, screenWidth, 8);
        mProjectFooterAdapter.setFootViewText("加载中");
        mFooterRey.setAdapter(mProjectFooterAdapter);
        mProjectFooterAdapter.setOnItemClickListener(this);

        *//**
         * 横向recycleview  视频
         *//*
        videoRecyclerviewHorizontal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //设置适配器
        mVideoAdapter = new ProjectVideoAdapter(getApplicationContext(), screenWidth, 4);
        mVideoAdapter.setFootViewText("加载中");
        videoRecyclerviewHorizontal.setAdapter(mVideoAdapter);
        mVideoAdapter.setOnItemClickListener(this);

        //盈利分析
        brandYlfxAdapter = new BrandYlfxAdapter(R.layout.brand_ylfx_list_item, ylfxList, getApplicationContext());
        brand_ylfx_lv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        brand_ylfx_lv.setAdapter(brandYlfxAdapter);

    }

    *//**
     * 获取projectCommentRecycleview滑动的y轴距离
     *
     * @return
     *//*
    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) projectCommentRecycleview.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    @Override
    protected void setListener() {

        projectDetailsFl.addOnLayoutChangeListener(this);

        projectCommentRecycleview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {

            }
        });

        //顶部视频滚动到不显示  暂停播放
        projectCommentRecycleview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (getScollYDistance() > 460) {
                    //JCVideoPlayer.releaseAllVideos();
                    if (project_video.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                        project_video.startButton.performClick();
                    }
                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        getBrandList();
    }

    *//**
     * 更新数据
     *
     * @param bean
     *//*
    private void setData(ProjectDetailsBean bean) {
        empty_tv.setVisibility(View.GONE);
        commentBottomLl.setVisibility(View.VISIBLE);

        if (projectDetailsBean != null) {

            totalPage = (bean.getPhoneComment().getTotal() - 1) / GlobalKeyContans.PAGE_SIZE + 1;
            project_details_title_tv.setText(projectDetailsBean.getCompany());
            if (projectDetailsBean.getPhoneComment() != null) {
                project_comment_total_numm_tv.setText(projectDetailsBean.getPhoneComment().getTotal() + "");
            }

            if (projectDetailsBean.getPromotes() != null && projectDetailsBean.getPromotes().size() > 1) {
                brand_detail_top_banner.setAutoPlayAble(true);
            } else {
                brand_detail_top_banner.setAutoPlayAble(false);
            }
            brand_detail_top_banner.setData(projectDetailsBean.getPromotes(), null);

            doIsAttentionOrIsAttestation();
            setBrandIntroductionText();
            doHbsKLogic();
            doRealStoreLogic();
            doBrandAdvangeLogic();
            doYlfxLogic();
            doFounderLogic(projectDetailsBean.getPhoneComment().getList());
            //精选问答模块
            if (projectDetailsBean.getAskRsp() != null && projectDetailsBean.getAskRsp().size() != 0) {
                mSelectorAdapter.setNewData(projectDetailsBean.getAskRsp());
            } else {
                mSelectLl.setVisibility(View.GONE);
            }
            doBrandRecommentLogic();
            doCommentLogic();
        }
    }

    @OnClick({R.id.share_ll, R.id.connect_agent_ll, R.id.keyboard_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_ll:
                if (projectDetailsBean != null) {
                    getShareUrl();
                }
                break;
            case R.id.connect_agent_ll:

                if (Preferences.isLogin()) {
                    getImAcc2();
                } else {
                    LoginActivity.start(NewProjectDetailsActivity.this);
                }
                break;
            case R.id.project_auth_ll:
                //认证相册查看
                if (projectDetailsBean != null && !beanIsEmpty(1)) {
                    imgPagerShow(0, 1);
                }
                break;
            case R.id.project_collection_ll:
                collectionOrNo();
                break;
            case R.id.keyboard_ll:
                //点击发表评论弹出输入框
                collectionOrNo();
                break;
            case R.id.investment_computer_rel:
                InvestmentComputerActivity.start(KDApplication.getContext(), projectDetailsBean);
                break;
            case R.id.join_area_ll:
                JoinAreaActivity.start(KDApplication.getContext(), brandId);
                break;
            case R.id.question_area_ll:
                showCommentPop();
                break;
            case R.id.selector_area_ll:
                showCommentPop();
                break;
            case R.id.comment_area_ll:
                showCommentPop();
                break;
            case R.id.footer_show_ll:
                mProjectCommentAdapter.setTimeTvShow(true);
                mProjectCommentAdapter.setLastViewShow(true);
                if (projectDetailsBean.getPhoneComment().getList().size() >= 10) {
                    mProjectCommentAdapter.setNewData(projectDetailsBean.getPhoneComment().getList().subList(0, 10));
                } else {
                    mProjectCommentAdapter.setNewData(projectDetailsBean.getPhoneComment().getList());
                }
                if (mMorePageNumber - 1 >= totalPage) {
//                    mOtherBrandRecommendLl.setVisibility(View.VISIBLE);
                    mFooterHideLl.setVisibility(View.VISIBLE);
                    mFooterShowLl.setVisibility(View.GONE);
                    mProjectCommentAdapter.loadMoreEnd(true);//不显示无更多数据
                } else {
                    mOtherBrandRecommendLl.setVisibility(View.GONE);
                    mFooterHideLl.setVisibility(View.GONE);
                    mFooterShowLl.setVisibility(View.GONE);
                    mProjectCommentAdapter.setEnableLoadMore(true);
                }
                break;
            case R.id.footer_hide_ll:
                mProjectCommentAdapter.setTimeTvShow(false);
                mProjectCommentAdapter.setLastViewShow(false);
                mProjectCommentAdapter.setNewData(commentList);
                mProjectCommentAdapter.setEnableLoadMore(false);
                mFooterShowLl.setVisibility(View.VISIBLE);
                mFooterHideLl.setVisibility(View.GONE);
                mMorePageNumber = 2;
                projectCommentRecycleview.scrollToPosition(mProjectCommentAdapter.getItemCount() - 1);
                break;
        }
    }

    *//**
     * 关注  取消关注
     *//*
    private void collectionOrNo() {
        //如果已经关注 取消
        if (Preferences.isLogin()) {
            if (projectDetailsBean != null && projectDetailsBean.isAttention()) {
                //取消收藏埋点
                JSONObject object = new JSONObject();
                try {
                    object.put("name", projectTitle);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                GrowingIO.getInstance().track("brand_detail_disfouce", object);
                cancelCollection();
            } else {
                //关注埋点
                JSONObject object = new JSONObject();
                try {
                    object.put("name", projectTitle);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                GrowingIO.getInstance().track("brand_detail_fouce", object);
                collection();
            }
        } else {
            LoginActivity.start(NewProjectDetailsActivity.this);
        }
    }

    *//**
     * 评论弹窗
     *//*
    private void showCommentPop() {
        if (Preferences.isLogin()) {
            commentBottomLl.setVisibility(View.GONE);
            if (mInputWindow == null) {
                mInputWindow = new PopupInputWindow(mActivity);
            }
            mInputWindow.showPopup(keyboardLl, new PopupInputWindow.OnInputListener() {
                @Override
                public void inputString(String comment) {
                    sendComment(comment);
                }
            });
        } else {
            showLoginDialog();
        }
    }

    *//**
     * 关注接口
     *//*
    private void collection() {
        showLoadingDialog();
        HashMap<String, String> params = ParamsUtils.getParams();
        params.put("busId", brandId);
        params.put("userId", Preferences.getKDAccountId());
        OkGo.post(AddressContants.API_SERVER_COLLECTION_UP)
                .tag(this)
                .upJson(ParamsUtils.getJson(params))
                .execute(new JsonCallback<LzyResponse<Object>>() {

                    @Override
                    public void onSuccess(LzyResponse<Object> simpleResponse, Call call, Response response) {
                        ToastUtils.showShortToast(R.string.collection_sucess);
                        project_collection_img.setImageResource(R.mipmap.icon_attention_hover);
                        brandCollectionImg.setImageResource(R.mipmap.icon_follow_hover);
                        guanzhu_tv.setText("已关注");
                        guanzhu_tv.setTextColor(getResources().getColor(R.color.color_279683));
                        projectDetailsBean.setAttention(true);
                        dismissLoadingDialog();
                        attention_number_tv.setText(Integer.parseInt(attention_number_tv.getText().toString().substring(0, (attention_number_tv.getText().toString()).length() - 1)) + 1 + "人");
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
//                        ToastUtils.showShortToast(e.getMessage());
                        dismissLoadingDialog();
                    }
                });
    }

    *//**
     * 取消关注接口
     *//*
    private void cancelCollection() {
        showLoadingDialog();
        HashMap<String, String> params = ParamsUtils.getParams();
        params.put("busId", brandId);
        params.put("userId", Preferences.getKDAccountId());
        OkGo.post(AddressContants.API_SERVER_COLLECTION_DOWN)
                .tag(this)
                .upJson(ParamsUtils.getJson(params))
                .execute(new JsonCallback<LzyResponse<Object>>() {

                    @Override
                    public void onSuccess(LzyResponse<Object> simpleResponse, Call call, Response response) {
                        ToastUtils.showShortToast(R.string.cancel_collection_sucess);
                        project_collection_img.setImageResource(R.mipmap.icon_guanzhu_normal);
                        brandCollectionImg.setImageResource(R.mipmap.icon_follow);
                        guanzhu_tv.setText("关注");
                        guanzhu_tv.setTextColor(getResources().getColor(R.color.color_666666));
                        projectDetailsBean.setAttention(false);
                        dismissLoadingDialog();
                        attention_number_tv.setText(Integer.parseInt(attention_number_tv.getText().toString().substring(0, (attention_number_tv.getText().toString()).length() - 1)) - 1 + "人");
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
//                        ToastUtils.showShortToast(e.getMessage());
                        dismissLoadingDialog();
                    }
                });
    }

    *//**
     * 发表评论
     *//*
    private void sendComment(String content) {
        HashMap<String, String> params = ParamsUtils.getParams();
        params.put("brandId", brandId);
        params.put("commentPerson", Preferences.getKDAccountId());
        params.put("commentContent", content);
        params.put("commentType", "1");
        OkGo.post(AddressContants.API_SERVER_SEND_COMMENT)
                .tag(this)
                .upJson(ParamsUtils.getJson(params))
                .execute(new JsonCallback<LzyResponse<Object>>() {

                    @Override
                    public void onSuccess(LzyResponse<Object> simpleResponse, Call call, Response response) {
                        ToastUtils.showShortToast(R.string.send_sucess);
                        mInputWindow.setDataEmpty();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
//                        ToastUtils.showShortToast(e.getMessage());
                    }
                });
    }

    *//**
     * type=1  是认证图片列表
     * type=2  是环境实景图片列表
     * type=3  是产品图片列表
     * type=4  是视频列表
     *
     * @param position 显示页标
     * @param type
     *//*
    private void imgPagerShow(int position, int type) {
        imagPagerUtil = new ImagPagerUtil(NewProjectDetailsActivity.this, projectDetailsBean, position, type);
        imagPagerUtil.show();
    }

    @Override
    public void onItemClick(View view, int postion, int type) {
        switch (type) {
            case 8:
                NewProjectDetailsActivity.start(getApplicationContext(),
                        projectDetailsBean.getBrandRecommendRsp().getList().get(postion).getBrandName(),
                        projectDetailsBean.getBrandRecommendRsp().getList().get(postion).getBrandId());
                break;
            case 2:
                if (!beanIsEmpty(2)) {
                    imgPagerShow(postion, 2);
                }
                break;
            case 3:
                if (!beanIsEmpty(3)) {
                    imgPagerShow(postion, 3);
                }
                break;
            case 5:
                //门店实景点击埋点
                JSONObject object = new JSONObject();
                try {
                    object.put("name", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                GrowingIO.getInstance().track("brand_detail_shop", object);
                if (!beanIsEmpty(5)) {
                    imgPagerShow(postion, 5);
                }
                break;
            case 4:
                //门店实景点击埋点
                JSONObject object1 = new JSONObject();
                try {
                    object1.put("name", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                GrowingIO.getInstance().track("brand_detail_video", object1);
                //播放视频去吧
                if (strList != null) {
                    strList.clear();
                }
                if (imageList != null) {
                    imageList.clear();
                }
                for (int i = 0; i < projectDetailsBean.getVideo().getList().size(); i++) {
                    strList.add(projectDetailsBean.getVideo().getList().get(i).getVideoUrl());
                    imageList.add(projectDetailsBean.getVideo().getList().get(i).getCoverdUrl());
                }
                ProjectDetailsJcVideoPlayActivity.start(this, strList, imageList, postion, projectDetailsBean.getVideo().getList().get(postion).getDuration(), projectDetailsBean.getVideo().getList().get(postion).getSize());

                // ProjectDetailsVideoPlayActivity.start(this, strList, postion);

                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
    }


    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            commentBottomLl.setVisibility(View.VISIBLE);
        }
    }

    *//**
     * 获取项目详情列表数据
     *//*
    private void getBrandList() {
        long time = System.currentTimeMillis();
        String str = String.valueOf(time);

        HashMap<String, String> params = new HashMap<>();
        params.put("brandId", brandId);
        params.put("userId", Preferences.getKDAccountId());
        params.put("timeLine", str);
        OkGo.post(AddressContants.API_SERVER_GET_BRAND_INFO_LIST)
                .tag(this)
                .upJson(ParamsUtils.getJson(params))
                .execute(new JsonCallback<LzyResponse<ProjectDetailsBean>>() {

                    @Override
                    public void onSuccess(LzyResponse<ProjectDetailsBean> agentBeanLzyResponse, Call call, Response response) {
                        projectDetailsBean = agentBeanLzyResponse.data;
                        //是否下架
                        if (!String.valueOf(agentBeanLzyResponse.code).equals("21105")) {
                            setData(projectDetailsBean);
                        } else {
                            showTipDialog();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        udaptError(false, e.getMessage());
                    }
                });
    }

    private void getMoreCommentData(final boolean isRefresh) {

        long time = System.currentTimeMillis();//获取系统时间的13位的时间戳
        String str = String.valueOf(time);

        HashMap<String, String> params = new HashMap<>();
        params.put("brandId", brandId);
        params.put("timeLine", str);
        params.put("sortFlag", "desc");
        params.put("pageNum", mMorePageNumber + "");
        params.put("pageSize", GlobalKeyContans.PAGE_SIZE + "");
        OkGo.post(AddressContants.API_SERVER_GET_COMMENT_LIST)
                .tag(this)
                .upJson(ParamsUtils.getJson(params))
                .execute(new JsonCallback<LzyResponse<ProjectDetailsBean.PhoneCommentBean>>() {

                    @Override
                    public void onSuccess(LzyResponse<ProjectDetailsBean.PhoneCommentBean> agentBeanLzyResponse, Call call, Response response) {
                        phoneCommentBean = agentBeanLzyResponse.data;
                        loadMore(phoneCommentBean, isRefresh);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        udaptError(isRefresh, e.getMessage());
                    }
                });
    }

    private void udaptError(boolean isloadMore, String msg) {
        if (isloadMore) {
            mProjectCommentAdapter.loadMoreFail();
            mRefreshLayout.setEnabled(true);
        } else {
            if (mRefreshLayout != null) {
                mRefreshLayout.endRefreshing();
            }
            mProjectCommentAdapter.setEnableLoadMore(true);
            mEmptyView.setViewState(EmptyView.ViewState.ERROR);
            if (commentBottomLl != null)
                commentBottomLl.setVisibility(View.GONE);
        }

    }

    private void loadMore(ProjectDetailsBean.PhoneCommentBean bean, Boolean isLoadMore) {

        commentListBeen = bean.getList();

        if (isLoadMore) {
            mMorePageNumber++;
            mProjectCommentAdapter.addData(commentListBeen);
            mProjectCommentAdapter.loadMoreComplete();
            mRefreshLayout.endLoadingMore();

            if (mMorePageNumber - 1 >= totalPage) {
                mProjectCommentAdapter.loadMoreEnd(true);
                mHander.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mFooterHideLl.setVisibility(View.VISIBLE);
                        if (projectDetailsBean.getBrandRecommendRsp() != null) {
                            mOtherBrandRecommendLl.setVisibility(View.VISIBLE);
                        }
                    }
                }, 400);
            } else {
                mFounderLl.setVisibility(View.GONE);
                mFooterShowLl.setVisibility(View.GONE);
            }

        } else {
            mProjectCommentAdapter.setNewData(commentListBeen);
            mRefreshLayout.endRefreshing();
            mProjectCommentAdapter.setEnableLoadMore(true);

            if (mMorePageNumber - 1 >= totalPage) {
                mProjectCommentAdapter.loadMoreEnd(true);//不显示无更多数据
            } else {
                mProjectCommentAdapter.setEnableLoadMore(true);
            }
        }
    }


    *//**
     * 初始化 HeaderView
     *
     * @return
     *//*
    private View getHeaderView() {
        View headerView = View.inflate(mActivity, R.layout.new_project_details_header, null);
        brand_detail_top_banner = (BGABanner) headerView.findViewById(R.id.brand_detail_top_banner);
        initBanner();
        brand_ylfx_lv = (RecyclerView) headerView.findViewById(R.id.brand_ylfx_lv);
        mFounderHeaderImg = (CustomCircleImageView) headerView.findViewById(R.id.founder_header_img);
        brand_attestation_img = (ImageView) headerView.findViewById(R.id.brand_attestation_img);
        join_area_ll = (LinearLayout) headerView.findViewById(R.id.join_area_ll);
        mQuestionAreaLl = (LinearLayout) headerView.findViewById(R.id.question_area_ll);
        mFounderTagLl = (FixGridLayout) headerView.findViewById(R.id.founder_tag_ll);
        investment_computer_rel = (LinearLayout) headerView.findViewById(R.id.investment_computer_rel);
        mSelectorAreaLl = (LinearLayout) headerView.findViewById(R.id.selector_area_ll);
        mCommentAreaLl = (LinearLayout) headerView.findViewById(R.id.comment_area_ll);
        mSelectLl = (LinearLayout) headerView.findViewById(R.id.select_ll);
        mFounderLl = (LinearLayout) headerView.findViewById(R.id.founder_ll);
        mBottomLl = (LinearLayout) headerView.findViewById(R.id.brand_founder_bottom_ll);
        mTopLl = (CardView) headerView.findViewById(R.id.brand_founder_top_ll);
        mCommentRel = (RelativeLayout) headerView.findViewById(R.id.comment_rel);
        project_auth_tv = (TextView) headerView.findViewById(R.id.project_auth_tv);
        mFounderNameTv = (TextView) headerView.findViewById(R.id.founder_name_tv);
        mFounderIntroductionTv = (TextView) headerView.findViewById(R.id.founder_introduction_tv);
        mFounderDescribeTv = (TextView) headerView.findViewById(R.id.founder_describe_tv);
        guanzhu_tv = (TextView) headerView.findViewById(R.id.guanzhu_tv);
        brand_all_investment_tv = (TextView) headerView.findViewById(R.id.brand_all_investment_tv);
        attention_number_tv = (TextView) headerView.findViewById(R.id.attention_number_tv);
        shop_area_tv = (TextView) headerView.findViewById(R.id.shop_area_tv);
        main_product_tv = (TextView) headerView.findViewById(R.id.main_product_tv);
        contract_period_tv = (TextView) headerView.findViewById(R.id.contract_period_tv);
        join_money_tv = (TextView) headerView.findViewById(R.id.join_money_tv);
        productRecyclerviewHorizontal = (BetterRecyclerView) headerView.findViewById(R.id.product_recyclerview_horizontal);
        real_store_recycleview = (BetterRecyclerView) headerView.findViewById(R.id.real_store_recycleview);
        videoRecyclerviewHorizontal = (BetterRecyclerView) headerView.findViewById(R.id.video_recyclerview_horizontal);
        mSelectorRey = (RecyclerView) headerView.findViewById(R.id.selector_recycleview);
        setFocusableFalse(productRecyclerviewHorizontal);
        setFocusableFalse(real_store_recycleview);
        setFocusableFalse(videoRecyclerviewHorizontal);
        setFocusableFalse(brand_ylfx_lv);
        setFocusableFalse(mSelectorRey);

        project_video = (JCVideoPlayerStandardNoTitle) headerView.findViewById(R.id.project_video);
        project_auth_ll = (LinearLayout) headerView.findViewById(R.id.project_auth_ll);
        project_collection_ll = (LinearLayout) headerView.findViewById(R.id.project_collection_ll);
        real_store_ll = (LinearLayout) headerView.findViewById(R.id.real_store_ll);
        brand_hbsk_ll = (LinearLayout) headerView.findViewById(R.id.brand_hbsk_ll);
        brand_ppys_ll = (LinearLayout) headerView.findViewById(R.id.brand_ppys_ll);
        brand_ylfx_ll = (LinearLayout) headerView.findViewById(R.id.brand_ylfx_ll);
        project_details_title_tv = (TextView) headerView.findViewById(R.id.project_details_title_tv);
        project_comment_total_numm_tv = (TextView) headerView.findViewById(R.id.project_comment_total_numm_tv);
        empty_tv = (TextView) headerView.findViewById(R.id.empty_tv);
        project_collection_img = (ImageView) headerView.findViewById(R.id.project_collection_img);
        project_auth_ll.setOnClickListener(this);
        project_collection_ll.setOnClickListener(this);
        investment_computer_rel.setOnClickListener(this);
        join_area_ll.setOnClickListener(this);
        mQuestionAreaLl.setOnClickListener(this);
        mSelectorAreaLl.setOnClickListener(this);
        mCommentAreaLl.setOnClickListener(this);

        return headerView;
    }

    *//**
     * 初始化 FooterView
     *
     * @return
     *//*
    private View getFooterView() {
        View footerView = View.inflate(mActivity, R.layout.new_project_details_footer, null);
        mFooterShowLl = (LinearLayout) footerView.findViewById(R.id.footer_show_ll);
        mFooterHideLl = (LinearLayout) footerView.findViewById(R.id.footer_hide_ll);
        mOtherBrandRecommendLl = (LinearLayout) footerView.findViewById(R.id.other_brand_recommend_ll);
        mRootLineLl = (LinearLayout) footerView.findViewById(R.id.root_line_ll);
        mFooterRey = (BetterRecyclerView) footerView.findViewById(R.id.footer_rey);
        mFooterShowLl.setOnClickListener(this);
        mFooterHideLl.setOnClickListener(this);
        setFocusableFalse(mFooterRey);
        return footerView;
    }

    private void initBanner() {
        brand_detail_top_banner.setAdapter(new BGABanner.Adapter<ImageView, ProjectDetailsBean.PromotesBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, ProjectDetailsBean.PromotesBean model, int position) {
                itemView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.loadNoCenter(KDApplication.getContext(), model.getImgUrl(), itemView, R.mipmap.brand_top_banner);
            }
        });
        brand_detail_top_banner.setAutoPlayInterval(5000);
    }

    private void setFocusableFalse(RecyclerView recyclerView) {
        recyclerView.setFocusable(false);
        recyclerView.setFocusableInTouchMode(false);
    }

    *//**
     * 判断bean是否为空
     *
     * @param type
     * @return
     *//*
    private boolean beanIsEmpty(int type) {
        if (type == 1) {
            if (projectDetailsBean.getCertificateList().getList() == null || projectDetailsBean.getCertificateList().getList().size() == 0) {
                return true;
            }
            return false;

        } else if (type == 2) {
            if (projectDetailsBean.getAdvantageImg().getList() == null || projectDetailsBean.getAdvantageImg().getList().size() == 0) {
                return true;
            }
            return false;
        } else if (type == 3) {
            if (projectDetailsBean.getProduct().getList() == null || projectDetailsBean.getProduct().getList().size() == 0) {
                return true;
            }
            return false;
        } else if (type == 5) {
            if (projectDetailsBean.getOutdoorScene().getList() == null || projectDetailsBean.getOutdoorScene().getList().size() == 0) {
                return true;
            }
            return false;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(this);
        ShareManager.getInstance().onDestroy();
        if (mOutDoorSceneAdapter != null) {
            mOutDoorSceneAdapter.removeCallbacks();
        }
        if (mProjectFooterAdapter != null) {
            mProjectFooterAdapter.removeCallbacks();
        }
        if (mProductImgAdapter != null) {
            mProductImgAdapter.removeCallbacks();
        }
        if (mVideoAdapter != null) {
            mVideoAdapter.removeCallbacks();
        }
        if (mHander != null) {
            mHander.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
        fixInputMethodManagerLeak(this);
        LogUtil.i(TAG, "onDestory");
    }

    @Override
    public void onLoadMoreRequested() {
        //评论查看埋点
        JSONObject object = new JSONObject();
        try {
            object.put("name", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GrowingIO.getInstance().track("brand_detail_comm", object);
        mRefreshLayout.setEnabled(false);
        mFooterShowLl.setVisibility(View.GONE);
        mOtherBrandRecommendLl.setVisibility(View.GONE);
        getMoreCommentData(true);
    }

    *//**
     * 下架提示 弹窗
     *//*
    private void showTipDialog() {
        PopDialogUtils.getInstance().showBrandTipDialog("该品牌已下架，请您查看其他品牌", NewProjectDetailsActivity.this, new PopDialogUtils.OnSureClickListener() {
            @Override
            public void onSure() {
                NewProjectDetailsActivity.this.finish();
            }

            @Override
            public void onCancel() {

            }
        });
    }


    *//**
     * 根据品牌查询电话客服队列号
     * /custservice/v1.0/queueBrand/getQueueByBrandId
     *//*
    private void getQueueByBrandId() {
        showLoadingDialog();
        HashMap<String, String> params = ParamsUtils.getParams();
        params.put("brandId", brandId);//6d89dd3949e94f719c28dc295ee70a94
        OkGo.post(AddressContants.API_SERVER_GET_QUNOBYBRANDID)
                .tag(this)
                .upJson(ParamsUtils.getJson(params))
                .execute(new JsonCallback<LzyResponse<String>>() {

                    @Override
                    public void onSuccess(LzyResponse<String> simpleResponse, Call call, Response response) {
                        qno = simpleResponse.data;
                        //拨打电话埋点
                        JSONObject object = new JSONObject();
                        try {
                            object.put("name", projectTitle);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        GrowingIO.getInstance().track("brand_detail_phone", object);
                        CallPhoneManager.callPhonePhone(NewProjectDetailsActivity.this, qno);
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
//                        ToastUtils.showShortToast(e.getMessage());
                        dismissLoadingDialog();
                    }
                });
    }


    *//**
     * 获取IM客服账号
     * /custservice/v1.0/service/getImAcc
     *//*
    private void getImAcc() {

        showLoadingDialog();
        HashMap<String, String> params = ParamsUtils.getParams();
        params.put("customerIM", Preferences.getIMUserAccount());
        params.put("customerPhone", Preferences.getKDUserAccount());
        OkGo.post(AddressContants.API_SERVER_GET_IMACCOUNT)
                .tag(this)
                .upJson(ParamsUtils.getJson(params))
                .execute(new JsonCallback<LzyResponse<String>>() {

                    @Override
                    public void onSuccess(LzyResponse<String> simpleResponse, Call call, Response response) {
                        dismissLoadingDialog();
                        String im = simpleResponse.data;
                        logo = projectDetailsBean.getLogo();
                        BrandLinkAttachment attachment = new BrandLinkAttachment();
                        attachment.setId(brandId);
                        attachment.setCover(logo);
                        attachment.setName(projectTitle);
                        attachment.setInvest(projectDetailsBean.getInvesmentAmount());
                        IMMessage message = MessageBuilder.createCustomMessage(im, SessionTypeEnum.P2P, attachment);
                        SessionHelper.startP2PSessionCousm(NewProjectDetailsActivity.this, im, message);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
//                        ToastUtils.showShortToast(e.getMessage());
                        dismissLoadingDialog();
                    }
                });
    }


    *//**
     * 获取IM客服账号
     * /custservice/v1.0/service/getImAcc
     *//*
    private void getImAcc2() {

        showLoadingDialog();
        HashMap<String, String> params = ParamsUtils.getParams();
        params.put("customerIM", Preferences.getIMUserAccount());
        params.put("customerPhone", Preferences.getKDUserAccount());
        OkGo.post(AddressContants.API_SERVER_GET_IMACCOUNT)
                .tag(this)
                .upJson(ParamsUtils.getJson(params))
                .execute(new JsonCallback<LzyResponse<String>>() {

                    @Override
                    public void onSuccess(LzyResponse<String> simpleResponse, Call call, Response response) {

                        //联系经纪人埋点
                        JSONObject object = new JSONObject();
                        try {
                            object.put("name", projectTitle);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        GrowingIO.getInstance().track("brand_detail_agent", object);

                        dismissLoadingDialog();
                        String im = simpleResponse.data;
                        SessionHelper.startP2PSession(NewProjectDetailsActivity.this, im);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
//                        ToastUtils.showShortToast(e.getMessage());
                        dismissLoadingDialog();
                    }
                });
    }

    private void getShareUrl() {
        showLoadingDialog();
        HashMap<String, String> params = ParamsUtils.getParams();
        params.put("id", brandId);
        OkGo.post(AddressContants.API_BRAND_SHARE_URL)
                .tag(this)
                .upJson(ParamsUtils.getJson(params))
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(LzyResponse<String> listLzyResponse, Call call, Response response) {
                        mShareUrl = listLzyResponse.data;
                        logo = projectDetailsBean.getLogo();
                        String des = "项目：" + projectTitle + "\n行业：" + projectDetailsBean.getBrandIndustry();
                        //分享埋点
                        ShareManager.getInstance().GrowingIotrack(null, null, null, null, "name", projectTitle);
                        ShareManager.getInstance().showPopuWondow(mActivity, shareLl, mShareUrl, logo, des);
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoadingDialog();
                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ShareManager.getInstance().onActivityResult(requestCode, resultCode, data);
    }*/
}
