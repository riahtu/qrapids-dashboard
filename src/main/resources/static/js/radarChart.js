function drawChart() {
    for (i = 0; i < titles.length; ++i) {
        var a = document.createElement('a');
        var title = titles[i];
        if (titles[i].indexOf('<') > -1)
            title = titles[i].substr(0, titles[i].indexOf('<'));
        if (isdsi) { //if it is a radar chart for Detailed Strategic Indicators
            var urlLink = "../QualityFactors/CurrentChart" + representationMode+ "?id=" + ids[i] + "&name=" + title;
        } else { //if it is a radar chart for Quality Factors
            var name = getParameterByName('name');
            var id = getParameterByName('id');
            if (name.length != 0) //if we know from which Detailed Strategic Indicator we are coming
                var urlLink = "../Metrics/CurrentChart?id=" + ids[i] + "&si=" + name + "&siid=" + id + "&name=" + title;
            else
                var urlLink = "../Metrics/CurrentChart?id=" + ids[i] + "&name=" + title;
        }
        a.setAttribute("href", urlLink);
        a.innerHTML = titles[i];
        a.style.fontSize = "16px";
        var div = document.createElement('div');
        div.id = titles[i];
        div.style.display = "inline-block";
        div.style.margin = "0px 5px 60px 5px";
        var p = document.createElement('p');
        var ctx = document.createElement('canvas');
        ctx.id = 'canvas' + i;
        ctx.width = 400;
        ctx.style.display = "inline";
        document.getElementById("radarChart").appendChild(div).appendChild(ctx);
        div.appendChild(p).appendChild(a);
        ctx.getContext("2d");
        if (labels[i].length === 2) {
            labels[i].push(null);
        } else if (labels[i].length === 1) {
            labels[i].push(null);
            labels[i].push(null);
        }
        var dataset = [];
        var t = titles[i].split("<br/>");
        dataset.push({ // data
            label: t[0],
            backgroundColor: 'rgba(1, 119, 166, 0.2)',
            borderColor: 'rgb(1, 119, 166)',
            pointBackgroundColor: 'rgb(1, 119, 166)',
            pointBorderColor: 'rgb(1, 119, 166)',
            data: values[i],
            fill: false
        });
        console.log(categories);
        for (var k = categories.length-1; k >= 0; --k) {
            var fill = categories.length-1-k;
            if (k == categories.length-1) fill = true;
            dataset.push({
                label: categories[k].name,
                borderWidth: 1,
                backgroundColor: hexToRgbA(categories[k].color, 0.3),
                borderColor: hexToRgbA(categories[k].color, 0.3),
                pointHitRadius: 0,
                pointHoverRadius: 0,
                pointRadius: 0,
                pointBorderWidth: 0,
                pointBackgroundColor: 'rgba(0, 0, 0, 0)',
                pointBorderColor: 'rgba(0, 0, 0, 0)',
                data: [].fill.call({ length: labels[i].length }, categories[k].upperThreshold),
                fill: fill
            })
        }
        console.log(dataset);
        window.myLine = new Chart(ctx, {    //draw chart with the following config
            type: 'radar',
            data: {
                labels: labels[i],
                datasets: dataset
            },
            options: {
                title: {
                    display: false,
                    fontSize: 16,
                    text: titles[i]
                },
                responsive: false,
                legend: {
                    display: false
                },
                scale: {    //make y axis scale 0 to 1 and set maximum number of axis lines
                    ticks: {
                        min: 0,
                        max: 1,
                        maxTicksLimit: 5
                    }
                },
                tooltips: {
                    filter: function (tooltipItem) {
                        return tooltipItem.datasetIndex === 0;
                    }
                }
            }
        });
        //Warnings
        if (typeof warnings !== "undefined") {
            var text = "";
            warnings[i].forEach(function (message) {
                if (text !== "") {
                    text += "\n"
                }
                text += message;
            });

            if (text !== "") {
                addWarning(div, text);
            }
        }
    }
}

function addWarning(div, message) {
    var warning = document.createElement("span");
    warning.setAttribute("class", "glyphicon glyphicon-alert");
    warning.title = message;
    warning.style.paddingLeft = "1em";
    warning.style.fontSize = "15px";
    warning.style.color = "yellow";
    warning.style.textShadow = "-2px 0 2px black, 0 2px 2px black, 2px 0 2px black, 0 -2px 2px black";
    div.append(warning);
}

function hexToRgbA(hex,a=1){ // (hex color, opacity)
    var c;
    if(/^#([A-Fa-f0-9]{3}){1,2}$/.test(hex)){
        c= hex.substring(1).split('');
        if(c.length== 3){
            c= [c[0], c[0], c[1], c[1], c[2], c[2]];
        }
        c= '0x'+c.join('');
        return 'rgba('+[(c>>16)&255, (c>>8)&255, c&255].join(',')+','+ a +')';
    }
    throw new Error('Bad Hex');
}

window.onload = function() {
    getData();
};
