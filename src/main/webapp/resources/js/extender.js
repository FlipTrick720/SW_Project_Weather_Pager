function chartExtender() {
    var options = {
        plugins: [ChartDataLabels],
        data: {
            datasets: [{
                // Change options only for labels of THIS DATASET
                datalabels: {
                    color: '#16739b',
                    anchor: 'end',
                    align: 'top',
                    formatter: (val) => {
                        return val + ' mm';
                    }
                },
                minBarLength: 0,
            }, {
                datalabels: {
                    //don't show labels for the second dataset
                    display: false
                }
            }]
        }
    };

    //merge all options into the main chart options
    $.extend(true, this.cfg.config, options);
};