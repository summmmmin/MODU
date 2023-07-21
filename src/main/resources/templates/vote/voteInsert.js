function init(){
	// 
	getVoteList();


}


function getVoteList(){
	fetch("/modu/voteList"){}
	 .then(response =>response.json())
  	 .then(data => {
    console.log("성공")
    })

})