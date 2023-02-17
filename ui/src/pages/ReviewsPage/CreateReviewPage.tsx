import React, {useState} from 'react'

import MyHeader from 'pages/component/MyHeader';
import {Footer} from "../component/Footer";
import {useNavigate} from "react-router-dom";
import {Comment, Form} from 'semantic-ui-react'

export function CreateReview() {
    const [textReview, setText] = useState('')
    //const [user] =

    const navigate = useNavigate();
    /* const addReview = () => {
         const review: Review = {
             textReview,
          //   userId
         };
         ReviewService.saveReview(review).then(response => navigate("/"));
     }
    useEffect(() => {
         ReviewService.saveReview(Object(review))
             .then(response => setReview(response));
     }, []);*/


    /*  const addReview = () => {
          setReview([...review, { text: ''}])
      }*/
    return (
        <>
            <MyHeader/>


            <Comment.Group>
                <Comment>
                    <Comment.Content>
                        <Form>
                            <Form.TextArea label='Add review' placeholder='Write review...'
                                           value={textReview}
                                           onChange={e => setText(e.target.value)}/>
                            <Form.Button
                                /// onClick={addReview}
                            >Add</Form.Button>
                        </Form>
                    </Comment.Content>
                </Comment>
            </Comment.Group>

            <Footer/>
        </>
    )
}


