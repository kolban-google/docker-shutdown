let terminate=false

function sleep(ms) {
    return new Promise((resolve) => {
        setTimeout(resolve, ms);
    });
}

async function run() {
    let counter = 0
    while (!terminate) {
        counter=counter+1
        console.log("Counter: " + counter + ", now: " + new Date())
        await sleep(1000)
    }
    console.log("NodeJS application terminating")
}

process.on('SIGTERM', () => {
    console.log("SIGTERM Caught!");
    terminate=true
})
process.on('SIGINT', () => {
    console.log("SIGINT Caught!");
    terminate=true
})

run()