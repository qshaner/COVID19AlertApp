const functions = require('firebase-functions');
const request = require('request-promise')
const admin = require('firebase-admin')
admin.initializeApp()

const db = admin.firestore()
const fcm = admin.messaging()
exports.sendStatsToDevices = functions.pubsub.schedule('0 21 * * *')
    .timeZone("America/New_York")
    .onRun(async context => {
        const snapshot = await db.collection("tokens").get()
        const tokens = snapshot.docs.map(snap => snap.id)
        const options = {
            uri: "https://api.covidtracking.com/v1/us/current.json",
            json: true
        }

        return request(options).then(res => {
            console.log("Result: ", res)
            const stats = res[0]
            const positiveIncrease = stats.positiveIncrease
            const deathIncrease = stats.deathIncrease
            const payload = {
                notification: {
                    title: "COVID-19 Daily Updates",
                    body: `New Positive Cases: ${positiveIncrease}, New Deaths: ${deathIncrease}`
                }
            }
            return fcm.sendToDevice(tokens, payload).catch(err => console.log(err))
        })
    })