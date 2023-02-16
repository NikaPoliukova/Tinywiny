import React, {useEffect, useState} from 'react'
import {Form} from 'semantic-ui-react'
import {Review} from 'model/Review';
import MyHeader from 'pages/component/MyHeader';
import {Footer} from "../component/Footer";
import BucketService from "../../services/BucketService";
import ReviewService from "../../services/ReviewService";

export function CreateReview() {
    const [review, setReview] = useState<Review>();
    const [text, setText] = useState('')

   /* useEffect(() => {
        ReviewService.saveReview(Object(review))
            .then(response => setReview(response));
    }, []);*/


    /*  const addReview = () => {
          setReview([...review, { text: ''}])
      }*/
    return (
        <>
        <MyHeader/>
            Add new review
            <Form>
                <Form.TextArea label='Discription' placeholder='Write dicscription...'/>
                <Form.Button>Add</Form.Button>
            </Form>
            <Footer />
        </>
    )
}


