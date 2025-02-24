<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\RPAScriptsSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Config scripts';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="rpascripts-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create a script', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?=
    GridView::widget([
        'dataProvider' => $dataProvider,
//        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],
            'id',
            'name',
            'path:ntext',
            'comments:ntext',
            ['class' => 'yii\grid\ActionColumn',
                'template' => '{view} {update} {delete}',
                'buttons' => [
                    'stop' => function ($url, $model) {
                        return Html::a('<span class="">Stop</span>', $url, [
                                'title' => Yii::t('app', 'Stop the process'),
                        ]);
                    }
                ]
            ]
        ],
    ]);
    ?>

</div>
