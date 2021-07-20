let table = document.getElementById("purchaseTable");
let sumVal = 0
let tbodyRowCount = table.tBodies[0];
for (let i = 0; i < tbodyRowCount.rows.length; i++) {
    let value = tbodyRowCount.rows[i].cells[2].innerHTML.split("$")
    sumVal = sumVal + parseFloat(value[0]);
}
document.getElementById("phoneSum").innerHTML = "Sum of your purchases: " + sumVal + "$"